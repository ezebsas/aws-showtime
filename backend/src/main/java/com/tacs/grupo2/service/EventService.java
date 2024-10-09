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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    @Transactional
    public TicketDTO createTicket(TicketCreationDTO ticketCreationDTO, Long id) {
        var ticket = ticketMapper.toTicket(ticketCreationDTO, id);
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
