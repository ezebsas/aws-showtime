package com.tacs.grupo2.entity;

import lombok.Data;
import org.testcontainers.shaded.org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Event {
    private String id;
    private String name;
    private LocalDateTime date;
    private EventStatus status;
    private String venueId;
    private List<EventSection> sections = new ArrayList<>();
}
