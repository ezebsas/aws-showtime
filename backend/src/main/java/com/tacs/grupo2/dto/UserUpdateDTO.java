package com.tacs.grupo2.dto;

import com.tacs.grupo2.entity.Role;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserUpdateDTO {
    private String lastname;
    private String firstname;
    private String country;
}
