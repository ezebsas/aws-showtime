package com.tacs.grupo2.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class InternalUser extends User {
    private UserRole role;
}
