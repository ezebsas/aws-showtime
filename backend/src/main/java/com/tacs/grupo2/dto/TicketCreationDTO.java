package com.tacs.grupo2.dto;

import com.tacs.grupo2.entity.Event;
import com.tacs.grupo2.entity.EventSeat;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
public class TicketCreationDTO {
    String eventId;
    String eventSectionId;
    String userId;
    List<String> seatIds;
}
