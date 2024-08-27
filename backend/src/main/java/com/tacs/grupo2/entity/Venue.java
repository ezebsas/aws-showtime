package com.tacs.grupo2.entity;

import lombok.Data;

import java.util.List;

@Data
public class Venue {
    private String name;
    private String city;
    private String address;
    private List<Section> sections;
}
