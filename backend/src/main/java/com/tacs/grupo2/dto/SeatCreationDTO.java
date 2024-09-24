package com.tacs.grupo2.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class SeatCreationDTO {
    Integer number;
    String row;
}
