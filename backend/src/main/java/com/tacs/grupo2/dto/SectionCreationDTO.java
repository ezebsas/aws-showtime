package com.tacs.grupo2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record SectionCreationDTO(@NotBlank String name, @Positive Integer availableSeats) {
}
