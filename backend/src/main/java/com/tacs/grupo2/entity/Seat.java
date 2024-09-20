package com.tacs.grupo2.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Value
@Entity
@Table(name = "seats")
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer number;
    private String row;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "seat_id")
    private List<EventSeat> eventSeats;
}
