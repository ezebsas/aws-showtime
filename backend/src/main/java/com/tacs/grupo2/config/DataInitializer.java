package com.tacs.grupo2.config;

import com.tacs.grupo2.entity.*;
import com.tacs.grupo2.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {


    private final UserRepository userRepository;
    private final VenueRepository venueRepository;
    private final EventSeatRepository eventSeatRepository;
    private final EventSectionRepository eventSectionRepository;
    private final SeatRepository seatRepository;
    private final SectionRepository sectionRepository;
    private final TicketRepository ticketRepository;
    private final PasswordEncoder passwordEncoder;
    private final EventRepository eventRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("admin").isEmpty()) {
            ApplicationUser adminUser = ApplicationUser.builder()
                    .username("admin")
                    .lastname("Admin")
                    .firstname("Default")
                    .country("N/A")
                    .password(passwordEncoder.encode("admin")) // Ensure the password is hashed
                    .role(Role.ADMIN)
                    .build();
            userRepository.save(adminUser);
        }


        if (venueRepository.findByName("Estadio Único de La Plata").isEmpty()) {
            List<Venue> venues = List.of(
                    Venue.builder()
                            .name("Estadio Único de La Plata")
                            .city("La Plata")
                            .address("Av. 25 300")
                            .sections(List.of(
                                    Section.builder()
                                            .name("Popular")
                                            .seats(List.of(
                                                    Seat.builder().number(1).row("A").build(),
                                                    Seat.builder().number(2).row("B").build(),
                                                    Seat.builder().number(3).row("C").build(),
                                                    Seat.builder().number(4).row("D").build()
                                            ))
                                            .build(),
                                    Section.builder()
                                            .name("Platea")
                                            .seats(List.of(
                                                    Seat.builder().number(1).row("A").build(),
                                                    Seat.builder().number(2).row("B").build(),
                                                    Seat.builder().number(3).row("C").build(),
                                                    Seat.builder().number(4).row("D").build()
                                            ))
                                            .build()
                            ))
                            .build(),
                    Venue.builder()
                            .name("Estadio Monumental")
                            .city("Buenos Aires")
                            .address("Av. Pres. Figueroa Alcorta 7597")
                            .sections(List.of(
                                    Section.builder()
                                            .name("Popular")
                                            .seats(List.of(
                                                    Seat.builder().number(1).row("A").build(),
                                                    Seat.builder().number(2).row("B").build(),
                                                    Seat.builder().number(3).row("C").build(),
                                                    Seat.builder().number(4).row("D").build()
                                            ))
                                            .build(),
                                    Section.builder()
                                            .name("Platea")
                                            .seats(List.of(
                                                    Seat.builder().number(1).row("A").build(),
                                                    Seat.builder().number(2).row("B").build(),
                                                    Seat.builder().number(3).row("C").build(),
                                                    Seat.builder().number(4).row("D").build()
                                            ))
                                            .build()
                            ))
                            .build()
            );
            venueRepository.saveAll(venues);
        }
//        if (eventSeatRepository.findAll().isEmpty()) {
//            eventSeatRepository.save(
//                    EventSeat.builder()
//                            .seat(seatRepository.findById(1L).orElseThrow(() -> new RuntimeException("Seat not found")))
//                            .user(userRepository.findByUsername("admin").get())
//                            .build()
//            );
//        }



        if (eventRepository.findAll().isEmpty()) {
            Seat seat = seatRepository.findById(1L).orElseThrow(() -> new RuntimeException("Seat not found"));
            ApplicationUser user = userRepository.findByUsername("admin").get();

            EventSeat eventSeat = EventSeat.builder()
                    .status(EventSeatStatus.AVAILABLE)
                    .seat(seat)
                    .user(user)
                    .build();
            EventSection eventSection = EventSection.builder()
                    .price(BigDecimal.valueOf(1000.0))
                    .eventSeats(List.of(eventSeat))
                    .section(sectionRepository.findById(1L).orElseThrow(() -> new RuntimeException("Section not found")))
                    .build();
            Event event = Event.builder()
                    .name("recital foo fighters")
                    .date(LocalDateTime.now().plusDays(10))
                    .status(EventStatus.OPEN)
                    .venue(venueRepository.findById(1L).orElseThrow(() -> new RuntimeException("Venue not found")))
                    .eventSections(List.of(eventSection))
                    .build();
            eventRepository.save(event);

        }
        if(ticketRepository.findAll().isEmpty()){
            Ticket ticket = Ticket.builder()
                    .event(eventRepository.findById(1L).orElseThrow(() -> new RuntimeException("Event not found")))
                    .eventSeat(eventSeatRepository.findById(1L).orElseThrow(() -> new RuntimeException("EventSeat not found")))
                    .eventSection(eventSectionRepository.findById(1L).orElseThrow(() -> new RuntimeException("EventSection not found")))
                    .user(userRepository.findByUsername("admin").get())
                    .total(BigDecimal.valueOf(1000.0))
                    .build();
            ticketRepository.save(ticket);
        }

    }
}