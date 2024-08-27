package com.tacs.grupo2.entity;


import lombok.Data;

@Data
public class User {
    private Integer id;
    private String email;
    private String password;
    private String name;
    private String lastName;
    private String country;
    private String city;
    private String address;
    private String phone;
}
