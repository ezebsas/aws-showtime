package com.tacs.grupo2.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Ticket {
    private String id;
    private String endUserId;
    private String eventId;
    private String eventSectionId;
    private List<String> eventSeatIds;
    private BigDecimal total;
}
