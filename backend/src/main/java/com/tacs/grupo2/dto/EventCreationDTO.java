package com.tacs.grupo2.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class EventCreationDTO {
    private String name;
    private LocalDateTime date;
    private String venueId;
    private List<EventSectionCreationDTO> sections;
}
