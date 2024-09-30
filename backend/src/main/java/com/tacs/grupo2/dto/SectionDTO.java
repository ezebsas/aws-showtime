package com.tacs.grupo2.dto;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
public class SectionDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    List<SeatDTO> seats;
    List<EventSectionDTO> eventSection;
}
