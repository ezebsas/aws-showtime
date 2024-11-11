package com.tacs.grupo2.service;

import com.tacs.grupo2.dto.EventDTO;
import com.tacs.grupo2.dto.StatisticsDTO;
import com.tacs.grupo2.dto.TicketDTO;
import com.tacs.grupo2.repository.redis.StatsRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StatisticsService {

    private final EventService eventService;
    private final StatsRedisRepository statsRedisRepository;

    public StatisticsDTO calculateStatistics() {
        List<EventDTO> events = eventService.getEvents();
        List<TicketDTO> tickets = eventService.getTickets(1L); // Utilización de un ID de usuario ficticio

        return StatisticsDTO.builder()
                .totalEvents(events.size())
                .totalTicketsSold(tickets.size())
                .totalRevenue(tickets.stream()
                        .map(TicketDTO::getTotal)
                        .mapToDouble(BigDecimal::doubleValue)
                        .sum())
                .build();
    }
}
