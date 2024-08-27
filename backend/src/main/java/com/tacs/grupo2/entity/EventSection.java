package com.tacs.grupo2.entity;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class EventSection {
    private Event event;
    private Section section;
    private BigDecimal price;
}
