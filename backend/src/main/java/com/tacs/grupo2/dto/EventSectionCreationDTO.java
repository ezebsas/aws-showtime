package com.tacs.grupo2.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EventSectionCreationDTO {
    private String sectionId;
    private BigDecimal price;
}
