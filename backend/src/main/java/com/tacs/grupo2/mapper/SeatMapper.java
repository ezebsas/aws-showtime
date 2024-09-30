package com.tacs.grupo2.mapper;

import com.tacs.grupo2.dto.SeatCreationDTO;
import com.tacs.grupo2.dto.SeatDTO;
import com.tacs.grupo2.entity.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { EventSeatMapper.class })
public interface SeatMapper {

    @Mapping(target = "section", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "eventSeats", ignore = true)
    Seat toSeat(SeatCreationDTO seatCreationDTO);

    @Mapping(target = "sectionId", source = "section.id")
    SeatDTO toDTO(Seat seat);
}
