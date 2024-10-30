package com.tacs.grupo2.exceptions;

import com.tacs.grupo2.dto.ErrorResponseDTO;
import lombok.extern.slf4j.Slf4j;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponseDTO<String>> handleExpiredJwtException(ExpiredJwtException e) {
        ErrorResponseDTO<String> error = new ErrorResponseDTO<>(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorResponseDTO<String>> handleAuthException(AuthException e) {
        ErrorResponseDTO<String> error = new ErrorResponseDTO<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO<Map<String, String>>> handleInvalidArgument(MethodArgumentNotValidException e) {
        Map<String, String> errorMap = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
        ErrorResponseDTO<Map<String, String>> error = new ErrorResponseDTO<>(HttpStatus.BAD_REQUEST.value(), errorMap);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO<String>> handleEntityNotFoundException(EntityNotFoundException e) {
        ErrorResponseDTO<String> error = new ErrorResponseDTO<>(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO<String>> handleIllegalArgumentException(IllegalArgumentException e) {
        ErrorResponseDTO<String> error = new ErrorResponseDTO<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalStateException(IllegalStateException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EventCreationException.class)
    public ResponseEntity<ErrorResponseDTO<String>> handleEventCreationException(EventCreationException e) {
        ErrorResponseDTO<String> error = new ErrorResponseDTO<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDTO<String>> handleAccessDeniedException(AccessDeniedException e) {
        ErrorResponseDTO<String> error = new ErrorResponseDTO<>(HttpStatus.FORBIDDEN.value(), "Access Denied");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO<String>> handleRuntimeException(RuntimeException e) {
        log.error("Unhandled exception caught by GlobalExceptionHandler: ", e);
        ErrorResponseDTO<String> error = new ErrorResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO<String>> handleException(Exception e) {
        ErrorResponseDTO<String> error = new ErrorResponseDTO<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}