package com.tacs.grupo2.repository.redis;

import com.tacs.grupo2.utils.RedisTimeSeriesCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class StatsRedisRepository {
    private final JedisPool jedisPool;
    private final Jedis jedis;

    public void incrementCounter(String counter) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.incr(counter);
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
        }
    }

    public void addDoubleCounter(String counter, double value) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.incrByFloat(counter, value);
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
        }
    }

    public Long getLongCounter(String counter) {
        String count = jedis.get(counter);
        return count != null ? Long.parseLong(count) : 0L;
    }

    public void saveTicketPrice(long timestamp, Double price) {
        /*try {
            jedis.sendCommand(RedisTimeSeriesCommand.TS_ADD, "TICKET:PRICES", String.valueOf(timestamp), String.valueOf(price));
        } catch (Exception e) {
            // Log the error message
            System.out.println("Error saving ticket price: " + e.getMessage());
        }*/
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.sendCommand(RedisTimeSeriesCommand.TS_ADD, "TICKET:PRICES", String.valueOf(timestamp), String.valueOf(price));
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
        }
    }

    public Double ticketsRange(long sinceTime) {
        try {
            Object result = jedis.sendCommand(RedisTimeSeriesCommand.TS_REVRANGE, "TICKET:PRICES", "-", "+", "AGGREGATION", "SUM", String.valueOf(sinceTime), "COUNT", "1");
            if (result instanceof ArrayList) {
                List<?> resultList = (List<?>) result;
                if (!resultList.isEmpty() && resultList.get(0) instanceof List) {
                    List<?> innerList = (List<?>) resultList.get(0);
                    if (innerList.size() > 1 && innerList.get(1) instanceof byte[]) {
                        return Double.parseDouble(new String((byte[]) innerList.get(1)));
                    }
                }
            }
        } catch (Exception e) {
            // Log the error message
            System.out.println("Error getting ticket range: " + e.getMessage());
        }
        return 0.0;
    }

    public double getDoubleCounter(String counter) {
        String count = jedis.get(counter);
        return count != null ? Double.parseDouble(count) : 0.0;
    }
}