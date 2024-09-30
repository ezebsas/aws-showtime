package com.tacs.grupo2.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class SeatCreationDTO {
    @NotNull
    @PositiveOrZero
    Integer number;
    @NotBlank
    String row;
}
