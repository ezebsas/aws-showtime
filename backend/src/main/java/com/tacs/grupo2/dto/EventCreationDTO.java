package com.tacs.grupo2.dto;

import com.tacs.grupo2.entity.EventStatus;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder(toBuilder = true)
public class EventCreationDTO {
    String name;
    LocalDateTime date;
    String venueId;
    EventStatus status;
    List<EventSectionCreationDTO> eventSections;
}
