package com.tacs.grupo2.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
public class TicketCreationDTO {
    String userId;
    List<Long> seatIds;
}
