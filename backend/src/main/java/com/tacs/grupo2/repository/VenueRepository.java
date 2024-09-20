package com.tacs.grupo2.repository;

import com.tacs.grupo2.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VenueRepository extends JpaRepository<Venue,Long> {
    Optional<Venue> findByName(String name);
}
