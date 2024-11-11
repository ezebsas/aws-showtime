package com.tacs.grupo2.service;

import com.tacs.grupo2.dto.EventDTO;
import com.tacs.grupo2.entity.Ticket;
import com.tacs.grupo2.repository.TicketRepository;
import com.tacs.grupo2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
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

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void initCounters() {
        List<EventDTO> events = eventService.getEvents();
        List<Ticket> tickets = ticketRepository.findAll();
        Long usersCount = userRepository.count();
        redisTemplate.opsForValue().set("UNIQUE_USERS", String.valueOf(usersCount));
        redisTemplate.opsForValue().set("TOTAL_EVENTS", String.valueOf(events.size()));
        redisTemplate.opsForValue().set("TOTAL_TICKETS_SOLD", String.valueOf(tickets.size()));
        redisDoubleTemplate.opsForValue().set("TOTAL_REVENUE", String.valueOf(
                tickets.stream()
                        .map(Ticket::getTotal)
                        .mapToDouble(BigDecimal::doubleValue)
                        .sum())
        );
    }
}