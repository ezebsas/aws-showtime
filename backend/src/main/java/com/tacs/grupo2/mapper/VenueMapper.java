package com.tacs.grupo2.mapper;

import com.tacs.grupo2.dto.VenueDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.tacs.grupo2.entity.Venue;
import com.tacs.grupo2.dto.VenueCreationDTO;

@Mapper(componentModel = "spring", uses = { SectionMapper.class, EventMapper.class })
public interface VenueMapper {

    @Mapping(target = "events", ignore = true)
    @Mapping(target = "id", ignore = true)
    Venue toVenue(VenueCreationDTO venueCreationDTO);

    VenueDTO toDTO(Venue venue);
}
