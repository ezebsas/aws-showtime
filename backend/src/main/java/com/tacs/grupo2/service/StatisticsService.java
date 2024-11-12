package com.tacs.grupo2.service;

import com.tacs.grupo2.dto.StatisticsDTO;
import com.tacs.grupo2.repository.redis.StatsRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RequiredArgsConstructor
@Service
public class StatisticsService {

    private final StatsRedisRepository statsRedisRepository;

    public Integer getUniqueUsersCount(){
        return Math.toIntExact(statsRedisRepository.getLongCounter("UNIQUE_USERS"));
    }

    public void incrementCounter(String counter) {
        statsRedisRepository.incrementCounter(counter);
    }

    public void addDoubleCounter(String counter, double value) {
        statsRedisRepository.addDoubleCounter(counter, value);
    }

    public void saveTicketPriceTime(LocalDateTime localDateTime, double price) {
        statsRedisRepository.saveTicketPrice(localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli(), price);
    }

    public StatisticsDTO calculateStatistics() {
        return StatisticsDTO.builder()
                .uniqueUsers(statsRedisRepository.getLongCounter("UNIQUE_USERS"))
                .totalEvents(statsRedisRepository.getLongCounter("TOTAL_EVENTS"))
                .totalTicketsSold(statsRedisRepository.getLongCounter("TOTAL_TICKETS_SOLD"))
                .totalRevenue(statsRedisRepository.getDoubleCounter("TOTAL_REVENUE"))
                .build();
    }

    public double getHourlyRevenue() {
        return statsRedisRepository.ticketsRange(3600000);
    }
    public double getDailyRevenue() {
        return statsRedisRepository.ticketsRange(86400000);
    }
    public double getWeeklyRevenue() {
        return statsRedisRepository.ticketsRange(604800000);
    }
}
