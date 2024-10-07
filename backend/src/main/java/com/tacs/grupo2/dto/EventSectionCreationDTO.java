package com.tacs.grupo2.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record EventSectionCreationDTO(@NotNull Long sectionId, @PositiveOrZero BigDecimal price,
                                      @Positive Integer availableSeats) {
}
