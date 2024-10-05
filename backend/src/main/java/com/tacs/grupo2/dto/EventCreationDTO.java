package com.tacs.grupo2.dto;

import com.tacs.grupo2.entity.EventStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record EventCreationDTO(@NotBlank String name, @FutureOrPresent LocalDateTime date, @NotBlank String venueId,
                               @NotNull EventStatus status,
                               @NotEmpty @Valid List<EventSectionCreationDTO> eventSections, Integer maxSeatsPerUser) {
}
