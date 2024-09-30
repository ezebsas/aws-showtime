package com.tacs.grupo2.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class SeatDTO {
    Long id;
    Integer number;
    String row;
    Long sectionId;
}
