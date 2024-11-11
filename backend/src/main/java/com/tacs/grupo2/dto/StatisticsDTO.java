package com.tacs.grupo2.dto;

import lombok.Builder;
import lombok.Value;

import java.util.Map;

@Value
@Builder(toBuilder = true)
public class StatisticsDTO {
    long uniqueUsers;
    long totalEvents;
    long totalTicketsSold;
    double totalRevenue;
}

