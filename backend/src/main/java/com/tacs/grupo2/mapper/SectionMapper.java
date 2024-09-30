package com.tacs.grupo2.mapper;

import com.tacs.grupo2.dto.SectionCreationDTO;
import com.tacs.grupo2.dto.SectionDTO;
import com.tacs.grupo2.entity.Section;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { SeatMapper.class, EventSectionMapper.class })
public interface SectionMapper {

    @Mapping(target = "venue", ignore = true)
    @Mapping(target = "eventSection", ignore = true)
    @Mapping(target = "id", ignore = true)
    Section toSection(SectionCreationDTO sectionCreationDTO);

    SectionDTO toDTO(Section section);
}
