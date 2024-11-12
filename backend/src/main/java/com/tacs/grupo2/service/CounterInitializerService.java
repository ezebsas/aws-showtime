package com.tacs.grupo2.service;

import com.tacs.grupo2.dto.EventDTO;
import com.tacs.grupo2.entity.Ticket;
import com.tacs.grupo2.repository.TicketRepository;
import com.tacs.grupo2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CounterInitializerService {
    private final RedisTemplate<String, String> redisTemplate;
    private final RedisTemplate<String, String> redisDoubleTemplate;
    private final UserRepository userRepository;
    private final EventService eventService;
    private final TicketRepository ticketRepository;
    private final StatisticsService statsService;

    public void createTicketPriceTimeSeries() {
        try {
            //delete the timeseries if it already exists
            redisTemplate.execute((RedisCallback<Object>) connection -> {
                connection.execute("DEL", "TICKET:PRICES".getBytes());
                return null;
            });
            redisTemplate.execute((RedisCallback<Object>) connection -> {
                connection.execute("TS.CREATE", "TICKET:PRICES".getBytes(), "RETENTION".getBytes(), "0".getBytes(), "LABELS".getBytes(), "type".getBytes(), "ticket_price".getBytes());
                return null;
            });
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
        redisTemplate.opsForValue().set("UNIQUE_USERS", String.valueOf(usersCount));
        redisTemplate.opsForValue().set("TOTAL_EVENTS", String.valueOf(events.size()));
        redisTemplate.opsForValue().set("TOTAL_TICKETS_SOLD", String.valueOf(tickets.size()));
        redisDoubleTemplate.opsForValue().set("TOTAL_REVENUE", String.valueOf(
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