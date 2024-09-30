package com.tacs.grupo2.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Value
@Builder(toBuilder = true)
public class EventSectionCreationDTO {
    @NotNull
    Long sectionId;
    @Valid
    @NotEmpty
    List<EventSeatCreationDTO> eventSeats;
    @PositiveOrZero
    BigDecimal price;
}
