package com.tacs.grupo2.dto;

import lombok.*;

@Value
@Builder(toBuilder = true)
public class AuthResponseDTO {
    String token;
}