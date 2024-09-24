package com.tacs.grupo2.controller;

import com.tacs.grupo2.dto.AuthResponseDTO;
import com.tacs.grupo2.dto.LoginRequestDTO;
import com.tacs.grupo2.dto.RegisterRequestDTO;
import com.tacs.grupo2.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO request)
    {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO request)
    {
        return ResponseEntity.ok(authService.register(request));
    }
}
