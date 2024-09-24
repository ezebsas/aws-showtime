package com.tacs.grupo2.mapper;

import com.tacs.grupo2.entity.*;
import com.tacs.grupo2.repository.*;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperHelper {

    private final SectionRepository sectionRepository;
    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;
    private final SeatRepository seatRepository;
    private final EventSectionRepository eventSectionRepository;

    @Named("toSection")
    public Section toSection(Long sectionId) {
        return sectionRepository.findById(sectionId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid section ID: " + sectionId));
    }

    @Named("toEvent")
    public Event toEvent(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event ID: " + eventId));
    }

    @Named("toVenue")
    public Venue toVenue(Long venueId) {
        return venueRepository.findById(venueId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid venue ID: " + venueId));
    }

    @Named("toSeat")
    public Seat toSeat(Long seatId) {
        return seatRepository.findById(seatId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid seat ID: " + seatId));
    }

    @Named("toEventSectionId")
    public EventSection toEventSection(Long eventSectionId) {
        return eventSectionRepository.findById(eventSectionId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event section ID: " + eventSectionId ));
    }
}
