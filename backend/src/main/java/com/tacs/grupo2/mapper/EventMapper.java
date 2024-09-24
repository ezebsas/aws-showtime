package com.tacs.grupo2.mapper;

import com.tacs.grupo2.dto.EventCreationDTO;
import com.tacs.grupo2.dto.EventDTO;
import com.tacs.grupo2.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {EventSectionMapper.class, MapperHelper.class})
public interface EventMapper {
    @Mapping(target = "venue", source = "venueId", qualifiedByName = "toVenue")
    @Mapping(target = "id", ignore = true)
    Event toEvent(EventCreationDTO eventCreationDTO);

    EventDTO toDTO(Event event);
}