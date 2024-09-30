package com.tacs.grupo2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class VenueCreationDTO {
    @NotBlank
    String name;
    String city;
    String address;
    @NotEmpty
    @Valid
    List<SectionCreationDTO> sections;
}
