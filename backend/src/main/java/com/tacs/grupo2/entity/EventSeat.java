package com.tacs.grupo2.entity;

import lombok.Data;

@Data
public class EventSeat {
    private Integer id;
    private EventSection section;
    private Seat seat;
    private EventSeatStatus status;
    private EndUser endUser;
}
