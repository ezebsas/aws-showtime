package com.tacs.grupo2.dto;

import com.tacs.grupo2.entity.EventStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder(toBuilder = true)
public class EventCreationDTO {
    @NotBlank
    String name;
    @FutureOrPresent
    LocalDateTime date;
    @NotBlank
    String venueId;
    @NotNull
    EventStatus status;
    @NotEmpty
    @Valid
    List<EventSectionCreationDTO> eventSections;
}
