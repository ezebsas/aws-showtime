package com.tacs.grupo2.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Data
public class EventSection {
    private String id;
    private String sectionId;
    private String eventId;
    private List<EventSeat> eventSeats = new ArrayList<>();
    private BigDecimal price;
}
