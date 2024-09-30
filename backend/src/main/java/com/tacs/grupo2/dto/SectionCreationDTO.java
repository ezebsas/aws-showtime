package com.tacs.grupo2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
public class SectionCreationDTO {
    @NotBlank
    String name;
    @NotEmpty
    @Valid
    List<SeatCreationDTO> seats;
}
