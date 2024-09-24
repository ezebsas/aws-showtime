package com.tacs.grupo2.dto;

import com.tacs.grupo2.entity.Event;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class VenueCreationDTO {
    String name;
    String city;
    String address;
    List<SectionCreationDTO> sections;
}
