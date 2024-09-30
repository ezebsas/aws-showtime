package com.tacs.grupo2.controller;

import com.tacs.grupo2.dto.TicketCreationDTO;
import com.tacs.grupo2.dto.TicketDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import com.tacs.grupo2.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<EntityModel<TicketDTO>> createTicket(@RequestBody @Valid TicketCreationDTO ticketDetails) {
        TicketDTO ticket = eventService.createTicket(ticketDetails);
        WebMvcLinkBuilder location = linkTo(methodOn(TicketController.class).getTicket(ticket.getId()));
        return ResponseEntity.created(location.toUri()).body(EntityModel.of(ticket, location.withSelfRel()));
    }

    @GetMapping
    public ResponseEntity<CollectionModel<TicketDTO>> getTickets(@RequestParam Long userId) {
        List<TicketDTO> tickets = eventService.getTickets(userId);
        return ResponseEntity.ok(CollectionModel.of(tickets, linkTo(methodOn(TicketController.class).getTickets(userId)).withSelfRel()));
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<EntityModel<TicketDTO>> getTicket(@PathVariable Long ticketId) {
        return ResponseEntity.ok(EntityModel.of(eventService.getTicket(ticketId), linkTo(methodOn(TicketController.class).getTicket(ticketId)).withSelfRel()));
    }
}
