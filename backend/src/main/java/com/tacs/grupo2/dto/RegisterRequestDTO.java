package com.tacs.grupo2.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Value
@Builder(toBuilder = true)
public class RegisterRequestDTO {
    @NotBlank
    String username;
    String password;
    @NotBlank
    String firstname;
    @NotBlank
    String lastname;
    @NotBlank
    String country;
}
