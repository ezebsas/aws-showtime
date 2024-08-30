package com.tacs.grupo2.controller;

import com.tacs.grupo2.dto.TicketCreationDTO;
import com.tacs.grupo2.entity.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import com.tacs.grupo2.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<Void> createTicket(@RequestBody TicketCreationDTO ticketDetails) {
        eventService.createTicket(ticketDetails);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getTickets(@RequestParam String userId) {
        return ResponseEntity.ok(eventService.getTickets(userId));
    }
}
