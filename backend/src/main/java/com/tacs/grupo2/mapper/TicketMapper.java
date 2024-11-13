package com.tacs.grupo2.mapper;

import com.tacs.grupo2.dto.TicketCreationDTO;
import com.tacs.grupo2.dto.TicketDTO;
import com.tacs.grupo2.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {MapperHelper.class, EventSectionMapper.class})
public interface TicketMapper {

    @Mapping(target = "total", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "eventSection", source = "ticketCreationDTO.eventSection", qualifiedByName = "toEventSectionId")
    @Mapping(target = "user", source = "userId", qualifiedByName = "toUser")
    Ticket toTicket(TicketCreationDTO ticketCreationDTO, Long userId);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "eventSectionId", source = "eventSection.id")
    @Mapping(target = "createdAt", source = "createdAt")
    TicketDTO toDTO(Ticket ticket);
}
