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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

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

    public EventDTO createEvent(EventCreationDTO eventCreationDTO) {
        var event = eventMapper.toEvent(eventCreationDTO);
        List<String> errorList = event.validate();
        if (!errorList.isEmpty()) {
            throw new EventCreationException(errorList);
        }

        return eventMapper.toDTO(eventRepository.save(event));
    }

    @PersistenceContext
    private EntityManager entityManager;

    /*
   @Transactional
   public TicketDTO createTicket(TicketCreationDTO ticketCreationDTO, Long id) {
       int maxRetries = 10;
       int attempt = 0;

       log.info("Creating ticket for event section with id: " + id);
       while (attempt < maxRetries) {
           try {
               log.info("Attempt: " + attempt);
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

               return ticketMapper.toDTO(savedTicket);
           } catch (ObjectOptimisticLockingFailureException e) {
               //log the exception and retry
                log.warn("Optimistic lock exception while creating ticket, retrying", e);
               attempt++;
               if (attempt >= maxRetries) {
                   throw new IllegalStateException("Failed to create ticket after " + maxRetries + " attempts", e);
               }
           }
       }
       throw new IllegalStateException("Could not create ticket, try again later");
   }
   */
    @Transactional
    public TicketDTO createTicket(TicketCreationDTO ticketCreationDTO, Long id) {
        int maxRetries = 10;
        int attempt = 0;

        while (attempt < maxRetries) {
            try {
                return createTicketWithNewTransaction(ticketCreationDTO, id);
            } catch (ObjectOptimisticLockingFailureException e) {
                log.warn("Optimistic lock exception while creating ticket, retrying", e);
                attempt++;
                if (attempt >= maxRetries) {
                    throw new IllegalStateException("Failed to create ticket after " + maxRetries + " attempts", e);
                }
            }
        }
        throw new IllegalStateException("Could not create ticket, try again later");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = ObjectOptimisticLockingFailureException.class)
    public TicketDTO createTicketWithNewTransaction(TicketCreationDTO ticketCreationDTO, Long id) {
        log.info("Entro a createTicketWithNewTransaction");
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

        return ticketMapper.toDTO(savedTicket);
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
