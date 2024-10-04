package com.tacs.grupo2.controller;

import com.tacs.grupo2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tacs.grupo2.dto.UserDTO;
import com.tacs.grupo2.dto.UserUpdateDTO;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<CollectionModel<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.findAll();
        return ResponseEntity.ok(CollectionModel.of(users));
    }

    @GetMapping("/me")
    public ResponseEntity<EntityModel<UserDTO>> getCurrentUser(){
        UserDTO userDTO = userService.getLoggedUserDTO();
        return ResponseEntity.ok(EntityModel.of(userDTO));
    }

    @PatchMapping("/me")
    public ResponseEntity<EntityModel<UserDTO>> patchCurrentUser(@RequestBody UserUpdateDTO userUpdate){
        UserDTO updatedUser = userService.updateUser(userUpdate);
        return ResponseEntity.ok(EntityModel.of(updatedUser));
    }
}
