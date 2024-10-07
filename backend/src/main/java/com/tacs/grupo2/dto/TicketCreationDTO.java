package com.tacs.grupo2.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TicketCreationDTO(@NotNull Long eventSection, @NotNull @Positive Integer quantity) {
}
