package com.tacs.grupo2.mapper;

import com.tacs.grupo2.dto.EventSectionCreationDTO;
import com.tacs.grupo2.dto.EventSectionDTO;
import com.tacs.grupo2.entity.EventSection;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {MapperHelper.class})
public interface EventSectionMapper {
    @Mapping(target = "event", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "section", source = "sectionId", qualifiedByName = "toSection")
    @Mapping(target = "availableSeats", source = "availableSeats")
    EventSection toEventSection(EventSectionCreationDTO eventSectionCreationDTO);


    @Mapping(target = "sectionId", source = "section.id")
    @Mapping(target = "eventId", source = "event.id")
    EventSectionDTO toDTO(EventSection eventSection);

    @AfterMapping
    default void setDefaultSeats(@MappingTarget EventSection eventSection) {
        if (eventSection.getAvailableSeats() == null) {
            eventSection.setAvailableSeats(eventSection.getSection().getAvailableSeats());
        }
    }
}
