package com.tacs.grupo2.dto.assembler;

import com.tacs.grupo2.controller.VenueController;
import com.tacs.grupo2.dto.VenueDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class VenueModelAssembler {

    public EntityModel<VenueDTO> toModel(VenueDTO venue) {
        return EntityModel.of(venue,
                linkTo(methodOn(VenueController.class).getVenue(venue.getId())).withSelfRel(),
                linkTo(methodOn(VenueController.class).getAllVenues()).withRel("allVenues"));
    }
}