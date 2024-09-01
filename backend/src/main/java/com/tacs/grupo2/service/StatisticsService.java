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
        StatisticsDTO statisticsDTO = new StatisticsDTO();

        List<Event> events = eventService.getEvents();
        List<Ticket> tickets = eventService.getTickets("UserIdExample"); // Utilización de un ID de usuario ficticio

        statisticsDTO.setTotalEvents(events.size());

        statisticsDTO.setTotalTicketsSold(tickets.size());
        statisticsDTO.setTotalRevenue(tickets.stream()
            .map(ticket -> eventService.getTotalPrice(ticket))
            .mapToDouble(price -> price.doubleValue()) 
            .sum());

        Map<String, Long> ticketsSoldPerEvent = new HashMap<>();
        Map<String, Long> revenuePerEvent = new HashMap<>();

        // Calculando boletos vendidos y ingresos por evento
        for (Event event : events) {
            long ticketsSold = tickets.stream()
                .filter(ticket -> ticket.getEventId().equals(event.getId()))
                .count();
            ticketsSoldPerEvent.put(event.getName(), ticketsSold);

            long revenue = tickets.stream()
                .filter(ticket -> ticket.getEventId().equals(event.getId()))
                .map(ticket -> eventService.getTotalPrice(ticket))
                .map(BigDecimal::longValue) // Conversion en long
                .reduce(0L, Long::sum);
            revenuePerEvent.put(event.getName(), revenue);
        }

        statisticsDTO.setTicketsSoldPerEvent(ticketsSoldPerEvent);
        statisticsDTO.setRevenuePerEvent(revenuePerEvent);

        return statisticsDTO;
    }
}
