package com.tacs.grupo2.repository;

import com.tacs.grupo2.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
