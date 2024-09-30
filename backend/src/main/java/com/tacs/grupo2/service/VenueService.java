package com.tacs.grupo2.service;

import com.tacs.grupo2.dto.VenueCreationDTO;
import com.tacs.grupo2.dto.VenueDTO;
import com.tacs.grupo2.mapper.VenueMapper;
import com.tacs.grupo2.repository.VenueRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueService {
    private final VenueRepository venueRepository;
    private final VenueMapper venueMapper;

    @Transactional
    public VenueDTO createVenue(VenueCreationDTO venueCreationDTO) {
        var venue = venueMapper.toVenue(venueCreationDTO);
        return venueMapper.toDTO(venueRepository.save(venue));
    }

    public List<VenueDTO> getVenues() {
        return venueRepository.findAll().stream().map(venueMapper::toDTO).toList();
    }

    public VenueDTO getVenue(Long id) {
        return venueRepository.findById(id)
                .map(venueMapper::toDTO)
                .orElseThrow();
    }
}
