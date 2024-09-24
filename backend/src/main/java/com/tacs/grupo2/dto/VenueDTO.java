package com.tacs.grupo2.dto;

import com.tacs.grupo2.entity.Event;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class VenueDTO {
    Long id;
    String name;
    String city;
    String address;
    List<SectionCreationDTO> sections;
    List<Event> events;
}
