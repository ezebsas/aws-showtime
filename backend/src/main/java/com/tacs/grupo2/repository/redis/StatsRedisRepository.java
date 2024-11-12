package com.tacs.grupo2.repository.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
        try {
            redisTemplate.execute((RedisCallback<Object>) connection -> {
                connection.execute("TS.ADD", "TICKET:PRICES".getBytes(), String.valueOf(timestamp).getBytes(), String.valueOf(price).getBytes());
                return null;
            });
        } catch (RedisSystemException e) {
            // Log the error message
            System.out.println("RedisSystemException: " + e.getMessage());
        } catch (DataAccessException e) {
            // Log the error message
            System.out.println("DataAccessException: " + e.getMessage());
        }
    }

    public Double ticketsRange(long sinceTime) {
        return redisTemplate.execute((RedisCallback<Double>) connection -> {
            Object result = connection.execute("TS.RANGE", "TICKET:PRICES".getBytes(), "-".getBytes(), "+".getBytes(), "AGGREGATION".getBytes(), "SUM".getBytes(), String.valueOf(sinceTime).getBytes(), "COUNT".getBytes(), "1".getBytes());
            if (result instanceof ArrayList) {
                List<?> resultList = (List<?>) result;
                if (!resultList.isEmpty() && resultList.get(0) instanceof List) {
                    List<?> innerList = (List<?>) resultList.get(0);
                    if (innerList.size() > 1 && innerList.get(1) instanceof byte[]) {
                        return Double.parseDouble(new String((byte[]) innerList.get(1)));
                    }
                }
            }
            return 0.0;
        });
    }
    public double getDoubleCounter(String counter) {
        String count = redisTemplate.opsForValue().get(counter);
        return count != null ? Double.parseDouble(count) : 0.0;
    }
}