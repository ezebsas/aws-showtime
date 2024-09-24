package com.tacs.grupo2.service;

import com.tacs.grupo2.dto.EventCreationDTO;
import com.tacs.grupo2.dto.TicketCreationDTO;
import com.tacs.grupo2.entity.*;
import com.tacs.grupo2.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.testcontainers.shaded.org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventService {
    private final VenueRepository venueRepository;

    private static final List<Event> events = new ArrayList<>();
    private static final List<Ticket> tickets = new ArrayList<>();

    public void createEvent(EventCreationDTO eventCreationDTO) {
        Venue venue = venueRepository.findById(Long.valueOf(eventCreationDTO.getVenueId())).orElseThrow();
        /*
        Venue venue = venues.stream().filter(v -> v.getId().equals(eventCreationDTO.getVenueId())).findFirst().orElseThrow();
        var event = new Event();

        //TODO: Eliminar cuando se implemente la generación de IDs de la base de datos
        event.setId(RandomStringUtils.random(10, true, true));

        event.setVenueId(venue.getId());
        eventCreationDTO.getSections().forEach(sectionCreationDTO -> {
            Section venueSection = venue.getSections().stream().filter(s -> s.getId().equals(sectionCreationDTO.getSectionId())).findFirst().orElseThrow();
            var eventSection = new EventSection();

            //TODO: Eliminar cuando se implemente la generación de IDs de la base de datos
            eventSection.setId(RandomStringUtils.random(10, true, true));


            eventSection.setSectionId(sectionCreationDTO.getSectionId());
            eventSection.setEventId(event.getId());
            venueSection.getSeats().forEach(seat -> {
                var eventSeat = new EventSeat();
                //TODO: Eliminar cuando se implemente la generación de IDs de la base de datos
                eventSeat.setId(RandomStringUtils.random(10, true, true));

                eventSeat.setSeatId(seat.getId());
                eventSeat.setEventSectionId(sectionCreationDTO.getSectionId());
                eventSeat.setEventId(event.getId());
                eventSeat.setStatus(EventSeatStatus.AVAILABLE);
                eventSection.getEventSeats().add(eventSeat);
            });
            eventSection.setPrice(sectionCreationDTO.getPrice());
            event.getSections().add(eventSection);
        });
        event.setName(eventCreationDTO.getName());
        event.setDate(eventCreationDTO.getDate());
        event.setStatus(EventStatus.OPEN);
        events.add(event);
        */
    }

    public void createTicket(TicketCreationDTO ticketDetails) {
        /*
        // Por ahora hardcodeado. La idea es que se obtenga del token del usuario logueado
        var endUser = users.stream().filter(user -> user.getId().equals(ticketDetails.getUserId())).findFirst().orElseThrow();
        var ticket = new Ticket();

        //TODO: Eliminar cuando se implemente la generación de IDs de la base de datos
        ticket.setId(RandomStringUtils.random(10, true, true));

        ticket.setEndUserId(endUser.getId());
        ticket.setEventSeatIds(ticketDetails.getSeatIds());
        ticket.setEventSectionId(ticketDetails.getEventSectionId());
        ticket.setEventId(ticketDetails.getEventId());
        ticket.setTotal(calculateTotalPrice(ticket));
        tickets.add(ticket);

         */
    }

    public List<Ticket> getTickets(String userId) {
        //return tickets.stream().filter(ticket -> ticket.getEndUserId().equals(userId)).toList();
        return new ArrayList<>();
    }

    public void closeEvent(String eventId) {
//        var eventToClose = events.stream().filter(event -> event.getId().equals(eventId)).findFirst().orElseThrow();
//        eventToClose.setStatus(EventStatus.CLOSED);
    }

    public BigDecimal calculateTotalPrice(Ticket ticket) {
//        var eventSection = events.stream().filter(event -> event.getId().equals(ticket.getEventId()))
//                .flatMap(event -> event.getSections().stream().filter(section -> section.getId().equals(ticket.getEventSectionId())))
//                .findFirst().orElseThrow();
//        return eventSection.getPrice().multiply(BigDecimal.valueOf(ticket.getEventSeatIds().size()));
        return BigDecimal.valueOf(0.0);
    }

    public List<Event> getEvents() {
        return events;
    }


    
}
