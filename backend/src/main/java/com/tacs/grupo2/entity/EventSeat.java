package com.tacs.grupo2.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "event_seats")
public class EventSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;
    @ManyToOne
    @JoinColumn(name = "event_section_id")
    private EventSection eventSection;
    @Enumerated(EnumType.STRING)
    private EventSeatStatus status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private ApplicationUser user;
    @OneToOne(mappedBy = "eventSeat", cascade = CascadeType.ALL, orphanRemoval = true)
    private Ticket ticket;
}
