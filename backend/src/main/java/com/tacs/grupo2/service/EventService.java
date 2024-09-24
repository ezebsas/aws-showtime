package com.tacs.grupo2.service;

import com.tacs.grupo2.dto.EventCreationDTO;
import com.tacs.grupo2.dto.TicketCreationDTO;
import com.tacs.grupo2.entity.*;
import com.tacs.grupo2.mapper.EventMapper;
import com.tacs.grupo2.repository.EventRepository;
import com.tacs.grupo2.repository.TicketRepository;
import com.tacs.grupo2.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.testcontainers.shaded.org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;
    private final EventMapper eventMapper;

    public void createEvent(EventCreationDTO eventCreationDTO) {
        var event = eventMapper.toEvent(eventCreationDTO);
        eventRepository.save(event);
    }

    public void createTicket(TicketCreationDTO ticketDetails) {
        ticketRepository.save(new Ticket());
    }

    public List<Ticket> getTickets(String userId) {
        return ticketRepository.findAll();
    }

    public void closeEvent(Long eventId) {
        eventRepository.findById(eventId).ifPresent(event -> {
            event.setStatus(EventStatus.CLOSED);
            eventRepository.save(event);
        });
    }

    public BigDecimal calculateTotalPrice(Ticket ticket) {
        return BigDecimal.valueOf(0.0);
    }

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long eventId) {
        return eventRepository.findById(eventId).orElseThrow();
    }
}
