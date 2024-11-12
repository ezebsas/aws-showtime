package com.tacs.grupo2.service;

import com.tacs.grupo2.dto.EventDTO;
import com.tacs.grupo2.entity.Ticket;
import com.tacs.grupo2.repository.TicketRepository;
import com.tacs.grupo2.repository.UserRepository;
import com.tacs.grupo2.utils.RedisTimeSeriesCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CounterInitializerService {
    private final Jedis jedis;
    private final UserRepository userRepository;
    private final EventService eventService;
    private final TicketRepository ticketRepository;
    private final StatisticsService statsService;

    public void createTicketPriceTimeSeries() {
        try {
            // Check if the time series exists and delete it if it does
            if (jedis.exists("TICKET:PRICES")) {
                jedis.del("TICKET:PRICES");
            }
            // Create the time series
            jedis.sendCommand(RedisTimeSeriesCommand.TS_CREATE, "TICKET:PRICES", "RETENTION", "0", "LABELS", "type", "ticket_price");
        } catch (Exception e) {
            // Log the exception or handle it as needed
            System.out.println("Error creating time series: " + e.getMessage());
        }
    }

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void initCounters() {
        List<EventDTO> events = eventService.getEvents();
        List<Ticket> tickets = ticketRepository.findAll();
        Long usersCount = userRepository.count();
        this.createTicketPriceTimeSeries();
        jedis.set("UNIQUE_USERS", String.valueOf(usersCount));
        jedis.set("TOTAL_EVENTS", String.valueOf(events.size()));
        jedis.set("TOTAL_TICKETS_SOLD", String.valueOf(tickets.size()));
        jedis.set("TOTAL_REVENUE", String.valueOf(
                tickets.stream()
                        .map(Ticket::getTotal)
                        .mapToDouble(BigDecimal::doubleValue)
                        .sum())
        );

        //save ticket prices from all tickets inserted on the database to redis timeseries
        tickets.forEach(ticket -> {
            statsService.saveTicketPriceTime(ticket.getCreatedAt(), ticket.getTotal().doubleValue());
        });
    }
}