package com.tacs.grupo2.entity;

import lombok.Data;

@Data
public class EventSeat {
    private String id;
    private String seatId;
    private String eventSectionId;
    private String eventId;
    private EventSeatStatus status;
    private String endUserId;
}
