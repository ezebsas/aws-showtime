package com.tacs.grupo2.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "event_section")
public class EventSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    private BigDecimal price;
    private Integer availableSeats;

    @Version
    private Integer version=0;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        EventSection that = (EventSection) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    public List<String> validate() {
        var errorList = new ArrayList<String>();
        if (availableSeats > section.getAvailableSeats()) {
            errorList.add("Available seats must be less than or equal to the section's available seats");
        }

        return errorList;
    }

    public void decreaseAvailableSeats(Integer quantity) {
        availableSeats -= quantity;
    }
}
