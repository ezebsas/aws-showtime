package com.tacs.grupo2.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Value
@Builder(toBuilder = true)
public class EventSectionDTO {
    Long id;
    Long sectionId;
    Long eventId;
    List<EventSeatDTO> eventSeats;
    BigDecimal price;
}
