package com.tacs.grupo2.repository.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StatsRedisRepository {
    private final RedisTemplate<String, String> redisTemplate;

    public StatsRedisRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void incrementCounter(String counter) {
        redisTemplate.opsForValue().increment(counter);
    }

    public Long getCounter(String counter) {
        String count = redisTemplate.opsForValue().get(counter);
        return count != null ? Long.parseLong(count) : 0L;
    }
}