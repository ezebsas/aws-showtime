package com.tacs.grupo2.repository;


import com.tacs.grupo2.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SectionRepository extends JpaRepository<Section,Long> {
    Optional<Section> findByName(String name);
}
