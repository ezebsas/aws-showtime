package com.tacs.grupo2.entity;

import lombok.Data;

import java.util.List;

@Data
public class Section {
    private Integer id;
    private List<Seat> seats;
    private String name;
}
