package com.tacs.grupo2.controller;

import com.tacs.grupo2.dto.EventCreationDTO;
import com.tacs.grupo2.dto.StatisticsDTO;
import com.tacs.grupo2.entity.Event;
import com.tacs.grupo2.service.EventService;
import com.tacs.grupo2.service.StatisticsService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @Autowired
    private StatisticsService statisticsService;

    @PostMapping
    public ResponseEntity<Void> createEvent(@RequestBody EventCreationDTO eventDetails) {
        eventService.createEvent(eventDetails);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
        public ResponseEntity<List<Event>> getEvents() {
        return ResponseEntity.ok(eventService.getEvents());
    }

    @PutMapping("/{eventId}/close")
    public ResponseEntity<Void> closeEvent(@PathVariable String eventId) {
        eventService.closeEvent(eventId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/statistics")
        public ResponseEntity<StatisticsDTO> getStatistics() {
        StatisticsDTO statisticsDTO = statisticsService.calculateStatistics();
        return ResponseEntity.ok(statisticsDTO);
    }
}
