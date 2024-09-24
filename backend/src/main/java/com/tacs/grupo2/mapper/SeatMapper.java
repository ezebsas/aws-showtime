package com.tacs.grupo2.mapper;

import com.tacs.grupo2.dto.SeatCreationDTO;
import com.tacs.grupo2.entity.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SeatMapper {

    @Mapping(target = "section", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "eventSeats", ignore = true)
    Seat toSeat(SeatCreationDTO seatCreationDTO);
}
