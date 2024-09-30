package com.tacs.grupo2.mapper;

import com.tacs.grupo2.dto.EventSeatCreationDTO;
import com.tacs.grupo2.dto.EventSeatDTO;
import com.tacs.grupo2.entity.EventSeat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {MapperHelper.class})
public interface EventSeatMapper {
    @Mapping(target = "eventSection", ignore = true) // Se puede establecer después
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "seat", source = "seatId", qualifiedByName = "toSeat")
    @Mapping(target = "ticket", ignore = true)
    EventSeat toEventSeat(EventSeatCreationDTO eventSeatCreationDTO);

    @Mapping(target = "seatId", source = "seat.id")
    @Mapping(target = "eventSectionId", source = "eventSection.id")
    EventSeatDTO toDTO(EventSeat eventSeat);
}

