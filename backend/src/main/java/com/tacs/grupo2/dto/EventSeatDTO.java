package com.tacs.grupo2.dto;

import com.tacs.grupo2.entity.EventSeatStatus;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class EventSeatDTO {
    Long id;
    Long seatId;
    Long eventSectionId;
    EventSeatStatus status;
}
