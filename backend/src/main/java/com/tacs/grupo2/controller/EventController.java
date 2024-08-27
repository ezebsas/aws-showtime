package com.tacs.grupo2.controller;

import com.tacs.grupo2.entity.Event;
import com.tacs.grupo2.entity.Ticket;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    @PostMapping
    public ResponseEntity<Void> createEvent(Event event) {

        System.out.println(event);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/tickets")
    public ResponseEntity<Void> buyTicket(Ticket ticket) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> getTickets(@RequestParam Integer userId) {
        return ResponseEntity.ok(List.of(new Ticket()));
    }

    @PutMapping("/event/{eventId}/close")
    public ResponseEntity<Void> closeEvent(@PathVariable Integer eventId) {
        return ResponseEntity.ok().build();
    }
}
