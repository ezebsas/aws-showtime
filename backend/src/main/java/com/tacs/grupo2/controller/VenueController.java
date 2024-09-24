package com.tacs.grupo2.controller;

import com.tacs.grupo2.dto.VenueCreationDTO;
import com.tacs.grupo2.dto.VenueDTO;
import com.tacs.grupo2.dto.assembler.VenueModelAssembler;
import com.tacs.grupo2.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
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
    private VenueModelAssembler assembler;

    @PostMapping
    public ResponseEntity<Void> createVenue(@RequestBody VenueCreationDTO venueCreationDTO) {
        venueService.createVenue(venueCreationDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public CollectionModel<EntityModel<VenueDTO>> getAllVenues() {
        List<EntityModel<VenueDTO>> venues = venueService.getVenues().stream()
                .map(assembler::toModel)
                .toList();
        return CollectionModel.of(venues, linkTo(methodOn(VenueController.class).getAllVenues()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<VenueDTO> getVenue(@PathVariable Long id) {
        return assembler.toModel(venueService.getVenue(id));
    }
}
