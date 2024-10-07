package com.tacs.grupo2.mapper;

import com.tacs.grupo2.dto.UserDTO;
import com.tacs.grupo2.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);
}
