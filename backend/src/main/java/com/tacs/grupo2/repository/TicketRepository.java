package com.tacs.grupo2.repository;

import com.tacs.grupo2.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
