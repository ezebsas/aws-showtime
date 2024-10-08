package com.tacs.grupo2.controller;

import com.tacs.grupo2.dto.EventCreationDTO;
import com.tacs.grupo2.dto.EventDTO;
import com.tacs.grupo2.dto.StatisticsDTO;
import com.tacs.grupo2.service.EventService;
import com.tacs.grupo2.service.StatisticsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final StatisticsService statisticsService;

    @PostMapping
    public ResponseEntity<EntityModel<EventDTO>> createEvent(@RequestBody @Valid EventCreationDTO eventCreationDTO) {
        EventDTO event = eventService.createEvent(eventCreationDTO);
        WebMvcLinkBuilder location = linkTo(methodOn(EventController.class).getEvent(event.getId()));
        return ResponseEntity.created(location.toUri()).body(EntityModel.of(event, location.withSelfRel()));
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EventDTO>> getEvents() {
        return ResponseEntity.ok(CollectionModel.of(eventService.getEvents(), linkTo(methodOn(EventController.class).getEvents()).withSelfRel()));
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EntityModel<EventDTO>> getEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(EntityModel.of(eventService.getEvent(eventId), linkTo(methodOn(EventController.class).getEvent(eventId)).withSelfRel()));
    }

    @PutMapping("/{eventId}/close")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> closeEvent(@PathVariable Long eventId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            System.out.println("Current user authorities: " + auth.getAuthorities());
        }

        eventService.closeEvent(eventId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/statistics")
    public ResponseEntity<StatisticsDTO> getStatistics() {
        StatisticsDTO statisticsDTO = statisticsService.calculateStatistics();
        return ResponseEntity.ok(statisticsDTO);
    }
}
