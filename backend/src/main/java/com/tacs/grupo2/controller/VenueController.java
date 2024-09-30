package com.tacs.grupo2.controller;

import com.tacs.grupo2.dto.VenueCreationDTO;
import com.tacs.grupo2.dto.VenueDTO;
import com.tacs.grupo2.dto.assembler.VenueModelAssembler;
import com.tacs.grupo2.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/venues")
@RequiredArgsConstructor
public class VenueController {
    private final VenueService venueService;
    private final VenueModelAssembler assembler;

    @PostMapping
    public ResponseEntity<EntityModel<VenueDTO>> createVenue(@RequestBody VenueCreationDTO venueCreationDTO) {
        var venue = venueService.createVenue(venueCreationDTO);
        WebMvcLinkBuilder location = linkTo(methodOn(VenueController.class).getVenue(venue.getId()));
        return ResponseEntity.created(location.toUri()).body(EntityModel.of(venue, location.withSelfRel()));
    }

    @GetMapping
    public ResponseEntity<CollectionModel<VenueDTO>> getAllVenues() {
        List<VenueDTO> venues = venueService.getVenues();
        return ResponseEntity.ok(CollectionModel.of(venues, linkTo(methodOn(VenueController.class).getAllVenues()).withSelfRel()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<VenueDTO>> getVenue(@PathVariable Long id) {
        return ResponseEntity.ok(assembler.toModel(venueService.getVenue(id)));
    }
}
