package com.tacs.grupo2.dto;

import com.tacs.grupo2.entity.Event;
import com.tacs.grupo2.entity.EventSeat;
import lombok.Data;

import java.util.List;

@Data
public class TicketCreationDTO {
    private String eventId;
    private String eventSectionId;
    private String userId;
    private List<String> seatIds;
}
