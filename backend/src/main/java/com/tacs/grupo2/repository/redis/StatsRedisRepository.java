package com.tacs.grupo2.repository.redis;

import com.tacs.grupo2.dto.EventDTO;
import com.tacs.grupo2.entity.Ticket;
import com.tacs.grupo2.repository.TicketRepository;
import com.tacs.grupo2.repository.UserRepository;
import com.tacs.grupo2.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class StatsRedisRepository {
    private final RedisTemplate<String, String> redisTemplate;

    public void incrementCounter(String counter) {
        redisTemplate.opsForValue().increment(counter);
    }
    public void addDoubleCounter(String counter, double value) {
        redisTemplate.opsForValue().increment(counter, value);
    }

    public Long getLongCounter(String counter) {
        String count = redisTemplate.opsForValue().get(counter);
        return count != null ? Long.parseLong(count) : 0L;
    }
    public void saveTicketPrice(long timestamp, Double price) {
        redisTemplate.opsForZSet().add("TICKET:PRICES", timestamp, price);
    }

    public double getDoubleCounter(String counter) {
        String count = redisTemplate.opsForValue().get(counter);
        return count != null ? Double.parseDouble(count) : 0.0;
    }

}