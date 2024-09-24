package com.tacs.grupo2.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.Map;

@Value
@Builder(toBuilder = true)
public class StatisticsDTO {
    long totalEvents;
    long totalTicketsSold;
    double totalRevenue;
    Map<String, Long> ticketsSoldPerEvent;
    Map<String, Long> revenuePerEvent;
}

