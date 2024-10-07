package com.tacs.grupo2.dto;

import com.tacs.grupo2.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Value;
import org.springframework.hateoas.server.core.Relation;

@Value
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String lastname;
    private String firstname;
    private String country;
    private Role role;
}
