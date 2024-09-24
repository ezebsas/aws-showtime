package com.tacs.grupo2.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "tickets")
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private ApplicationUser user;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    @ManyToOne
    @JoinColumn(name = "event_section_id")
    private EventSection eventSection;
    @OneToOne
    @JoinColumn(name = "event_seat_id")
    private EventSeat eventSeat;
    private BigDecimal total;
}
