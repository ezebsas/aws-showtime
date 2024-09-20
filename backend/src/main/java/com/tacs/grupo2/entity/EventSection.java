package com.tacs.grupo2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "event_sections")
public class EventSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "event_section_id")
    private List<EventSeat> eventSeats;
    private BigDecimal price;
    @OneToMany(mappedBy = "eventSection")
    List<Ticket> ticket;
}
