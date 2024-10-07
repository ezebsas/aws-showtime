package com.tacs.grupo2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.Valid;

import java.util.List;

public record VenueCreationDTO(@NotBlank String name, String city, String address,
                               @NotEmpty @Valid List<SectionCreationDTO> sections) {
}
