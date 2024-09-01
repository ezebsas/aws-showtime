package com.tacs.grupo2.dto;

import lombok.Data;
import java.util.Map;

@Data //genera los getters, setters, etc automaticamente
public class StatisticsDTO {
    private long totalEvents;
    private long totalTicketsSold;
    private double totalRevenue;
    private Map<String, Long> ticketsSoldPerEvent;
    private Map<String, Long> revenuePerEvent;
}

