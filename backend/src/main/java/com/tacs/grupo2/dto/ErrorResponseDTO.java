package com.tacs.grupo2.dto;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class ErrorResponseDTO<T> {
    private final LocalDateTime timestamp;
    private final int status;
    private final T error;

    public ErrorResponseDTO(int status, T error) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
    }
}
