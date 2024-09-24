package com.tacs.grupo2.service;

import com.tacs.grupo2.dto.AuthResponseDTO;
import com.tacs.grupo2.dto.LoginRequestDTO;
import com.tacs.grupo2.dto.RegisterRequestDTO;
import com.tacs.grupo2.entity.User;
import com.tacs.grupo2.entity.Role;
import com.tacs.grupo2.jwt.JwtService;
import com.tacs.grupo2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDTO login(LoginRequestDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user=userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponseDTO.builder()
                .token(token)
                .build();

    }

    public AuthResponseDTO register(RegisterRequestDTO request) {
        User user = new User();
                user.setUsername(request.getUsername());
                user.setPassword(passwordEncoder.encode( request.getPassword()));
                user.setFirstname(request.getFirstname());
                user.setLastname(request.getLastname());
                user.setCountry(request.getCountry());
                user.setRole(Role.USER);

        userRepository.save(user);

        return AuthResponseDTO.builder()
                .token(jwtService.getToken(user))
                .build();

    }

}