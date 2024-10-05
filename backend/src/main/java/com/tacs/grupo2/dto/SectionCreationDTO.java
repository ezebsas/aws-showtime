package com.tacs.grupo2.dto;

import jakarta.validation.constraints.NotBlank;

public record SectionCreationDTO(@NotBlank String name) {
}
