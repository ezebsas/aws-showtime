package com.tacs.grupo2.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Value
@Builder(toBuilder = true)
public class LoginRequestDTO {
    @NotBlank
    String username;
    String password;
}