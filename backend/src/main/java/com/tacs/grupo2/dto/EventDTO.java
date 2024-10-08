package com.tacs.grupo2.dto;

import com.tacs.grupo2.entity.EventStatus;
import lombok.Builder;
import lombok.Value;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder(toBuilder = true)
@Relation(collectionRelation = "events")
public class EventDTO {
    Long id;
    String name;
    LocalDateTime date;
    EventStatus status;
    Long venueId;
    List<EventSectionDTO> eventSections;
    Integer maxSeatsPerUser;
}