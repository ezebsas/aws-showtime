package com.tacs.grupo2.service;

import com.tacs.grupo2.dto.EventCreationDTO;
import com.tacs.grupo2.dto.EventDTO;
import com.tacs.grupo2.dto.TicketCreationDTO;
import com.tacs.grupo2.dto.TicketDTO;
import com.tacs.grupo2.entity.*;
import com.tacs.grupo2.exceptions.EntityNotFoundException;
import com.tacs.grupo2.exceptions.EventCreationException;
import com.tacs.grupo2.mapper.EventMapper;
import com.tacs.grupo2.mapper.TicketMapper;
import com.tacs.grupo2.repository.EventRepository;
import com.tacs.grupo2.repository.EventSectionRepository;
import com.tacs.grupo2.repository.TicketRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.OptimisticLockException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;
    private final EventMapper eventMapper;
    private final TicketMapper ticketMapper;
    private final EventSectionRepository eventSectionRepository;
    private final PlatformTransactionManager transactionManager;
    private final StatisticsService statsService;

    public EventDTO createEvent(EventCreationDTO eventCreationDTO) {
        var event = eventMapper.toEvent(eventCreationDTO);
        List<String> errorList = event.validate();
        if (!errorList.isEmpty()) {
            throw new EventCreationException(errorList);
        }
        EventDTO dto = eventMapper.toDTO(eventRepository.save(event));
        statsService.incrementCounter("TOTAL_EVENTS");
        return dto;
    }

    @PersistenceContext
    private EntityManager entityManager;

    public TicketDTO createTicket(TicketCreationDTO ticketCreationDTO, Long id) {
        int maxRetries = 10;
        int attempt = 1;
        long backoff = 500; // Initial backoff time in milliseconds
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);

        while (attempt < maxRetries) {
            try {
                log.info("Creating ticket for event section with  user_id: " + id + " attempt: " +attempt);
                return transactionTemplate.execute(status -> {
                    var ticket = ticketMapper.toTicket(ticketCreationDTO, id);
                    var eventSection = entityManager.find(EventSection.class, ticket.getEventSection().getId(), LockModeType.OPTIMISTIC);
                    var event = eventSection.getEvent();

                    if (event.getStatus() == EventStatus.CLOSED) {
                        throw new IllegalStateException("Cannot create ticket for a closed event");
                    }

                    if (ticket.getQuantity() > ticket.getEventSection().getAvailableSeats()) {
                        throw new IllegalArgumentException("Not enough available seats for the requested quantity");
                    }

                    if (ticket.getQuantity() > event.getMaxSeatsPerUser()) {
                        throw new IllegalArgumentException("Requested quantity exceeds the maximum seats per user for this event");
                    }

                    var savedTicket = ticketRepository.save(ticket);
                    ticket.getEventSection().decreaseAvailableSeats(ticket.getQuantity());
                    eventSectionRepository.save(ticket.getEventSection());

                    statsService.incrementCounter("TOTAL_TICKETS_SOLD");
                    statsService.addDoubleCounter("TOTAL_REVENUE", ticket.getTotal().doubleValue());
                    return ticketMapper.toDTO(savedTicket);
                });
            } catch (ObjectOptimisticLockingFailureException e) {
                log.warn("Optimistic lock exception while creating ticket, retrying");
                attempt++;
                if (attempt >= maxRetries) {
                    throw new IllegalStateException("Failed to create ticket after " + maxRetries + " attempts: ", e);
                }
                try {
                    Thread.sleep(backoff); // Wait before retrying
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
                backoff += 1000;
            }
        }
        throw new IllegalStateException("Could not create ticket, try again later");
    }

    public List<TicketDTO> getTickets(Long userId) {
        return ticketRepository.findByUserId(userId).stream().map(ticketMapper::toDTO).toList();
    }

    public void closeEvent(Long eventId) {
        eventRepository.findById(eventId).ifPresent(event -> {
            event.setStatus(EventStatus.CLOSED);
            eventRepository.save(event);
        });
    }

    public List<EventDTO> getEvents() {
        return eventRepository.findAll().stream().map(eventMapper::toDTO).toList();
    }

    public EventDTO getEvent(Long eventId) {
        return eventMapper.toDTO(eventRepository.findById(eventId).orElseThrow(() -> new EntityNotFoundException("Event with id: " + eventId + " doesn't exist")));
    }

    public TicketDTO getTicket(Long ticketId) {
        return ticketMapper.toDTO(ticketRepository.findById(ticketId).orElseThrow(() -> new EntityNotFoundException("Ticket with id: " + ticketId + " doesn't exist")));
    }
}
