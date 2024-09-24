package com.tacs.grupo2.service;

import com.tacs.grupo2.dto.StatisticsDTO;
import com.tacs.grupo2.entity.Event;
import com.tacs.grupo2.entity.Ticket;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsService {

    private final EventService eventService;

    public StatisticsService(EventService eventService) {
        this.eventService = eventService;
    }

    public StatisticsDTO calculateStatistics() {
        List<Event> events = eventService.getEvents();
        List<Ticket> tickets = eventService.getTickets("UserIdExample"); // Utilización de un ID de usuario ficticio

        return StatisticsDTO.builder()
                .totalEvents(events.size())
                .totalTicketsSold(tickets.size())
                .totalRevenue(tickets.stream()
                        .map(eventService::calculateTotalPrice)
                        .mapToDouble(BigDecimal::doubleValue)
                        .sum())
                .build();
    }
}
