package com.tacs.grupo2.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "event_seat")
public class EventSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;
    @ManyToOne
    @JoinColumn(name = "event_section_id")
    private EventSection eventSection;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventSeatStatus status;
    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        EventSeat eventSeat = (EventSeat) o;
        return getId() != null && Objects.equals(getId(), eventSeat.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
