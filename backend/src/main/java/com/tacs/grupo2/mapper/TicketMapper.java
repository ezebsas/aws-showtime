package com.tacs.grupo2.mapper;

import com.tacs.grupo2.dto.TicketCreationDTO;
import com.tacs.grupo2.dto.TicketDTO;
import com.tacs.grupo2.entity.EventSeat;
import com.tacs.grupo2.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {MapperHelper.class})
public interface TicketMapper {


    @Mapping(target = "total", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "eventSeats", source = "seatIds", qualifiedByName = "toEventSeat")
    @Mapping(target = "user", source = "userId", qualifiedByName = "toUser")
    Ticket toTicket(TicketCreationDTO ticketCreationDTO);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "eventSeatIds", source = "eventSeats")
    TicketDTO toDTO(Ticket ticket);

    default List<Long> mapEventSeatsToIds(List<EventSeat> eventSeats) {
        return eventSeats.stream()
                .map(EventSeat::getId)
                .collect(Collectors.toList());
    }
}
