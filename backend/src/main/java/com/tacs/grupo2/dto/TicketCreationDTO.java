package com.tacs.grupo2.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
public class TicketCreationDTO {
    @NotNull
    String userId;
    @NotEmpty
    List<Long> seatIds;
}
