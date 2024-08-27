package com.tacs.grupo2.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Event {
    private String id;
    private String name;
    private LocalDateTime date;
    private EventStatus status;
    private List<EventSection> sections;
}
