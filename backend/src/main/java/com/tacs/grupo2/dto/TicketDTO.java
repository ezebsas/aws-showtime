package com.tacs.grupo2.dto;

import lombok.Builder;
import lombok.Value;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.util.List;

@Value
@Builder(toBuilder = true)

@Relation(collectionRelation = "tickets")
public class TicketDTO {
    Long id;
    Long userId;
    List<Long> eventSeatIds;
    BigDecimal total;
}
