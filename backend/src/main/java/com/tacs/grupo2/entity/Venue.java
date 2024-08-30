package com.tacs.grupo2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venue {
    private String id;
    private String name;
    private String city;
    private String address;
    private List<Section> sections = new ArrayList<>();
}
