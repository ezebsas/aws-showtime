package com.tacs.grupo2.repository;

import com.tacs.grupo2.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<ApplicationUser,Integer> {
    Optional<ApplicationUser> findByUsername(String username);
}
