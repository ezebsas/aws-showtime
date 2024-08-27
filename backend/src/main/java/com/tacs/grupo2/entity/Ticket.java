package com.tacs.grupo2.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Ticket {
    private EndUser endUser;
    private List<EventSeat> seats;
    private BigDecimal total;
}
