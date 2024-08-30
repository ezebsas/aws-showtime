package com.tacs.grupo2.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class User {
    private String id;
    private List<Ticket> tickets;
    private String email;
    private String password;
    private String name;
    private String lastName;
    private String country;
    private String city;
    private String address;
    private String phone;
    private UserRole role;

    // Constructor creado solo para mocks. Eliminar cuando ya exista
    // un modelo real de usuario
    public User(String id, String email) {
        this.id = id;
        this.email = email;
    }
}
