package com.tacs.grupo2.dto;

import com.tacs.grupo2.entity.*;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class EventSeatCreationDTO {
    Long seatId;
    EventSeatStatus status;
}
