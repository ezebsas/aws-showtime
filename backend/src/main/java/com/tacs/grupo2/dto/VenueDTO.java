package com.tacs.grupo2.dto;

import com.tacs.grupo2.entity.Event;
import lombok.Builder;
import lombok.Value;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@Value
@Builder
@Relation(collectionRelation = "venues")
public class VenueDTO {
    Long id;
    String name;
    String city;
    String address;
    List<SectionDTO> sections;
    List<EventDTO> events;
}
