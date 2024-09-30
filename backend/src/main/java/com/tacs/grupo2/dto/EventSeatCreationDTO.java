package com.tacs.grupo2.dto;

import com.tacs.grupo2.entity.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class EventSeatCreationDTO {
    @NotNull
    Long seatId;
    @NotNull
    EventSeatStatus status;
}
