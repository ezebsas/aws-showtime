package com.tacs.grupo2.repository;

import com.tacs.grupo2.entity.ApplicationUser;
import com.tacs.grupo2.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat,Long> {

}
