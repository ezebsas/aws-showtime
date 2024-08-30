package com.tacs.grupo2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Section {
    private String id;
    private String name;
    private List<Seat> seats = new ArrayList<>();
}
