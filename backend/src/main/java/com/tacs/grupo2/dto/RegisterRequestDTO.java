package com.tacs.grupo2.dto;

import lombok.*;

@Value
@Builder(toBuilder = true)
public class RegisterRequestDTO {
    String username;
    String password;
    String firstname;
    String lastname;
    String country;
}
