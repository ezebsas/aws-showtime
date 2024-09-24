package com.tacs.grupo2.dto;

import com.tacs.grupo2.entity.EventStatus;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder(toBuilder = true)
public class EventDTO {
    Long id;
    String name;
    LocalDateTime date;
    EventStatus status;
    Long venueId;
    List<EventSectionDTO> eventSections;
}