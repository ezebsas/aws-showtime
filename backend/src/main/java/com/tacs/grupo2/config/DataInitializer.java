package com.tacs.grupo2.config;

import com.tacs.grupo2.entity.*;
import com.tacs.grupo2.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {
        if (!userRepository.existsByUsername("admin")) {
            User defaultAdmin = new User();
            defaultAdmin.setUsername("admin");
            defaultAdmin.setPassword(passwordEncoder.encode("admin"));
            defaultAdmin.setRole(Role.ADMIN);
            defaultAdmin.setFirstname("Admin");
            defaultAdmin.setLastname("Admin");
            userRepository.save(defaultAdmin);
        }
    }
}