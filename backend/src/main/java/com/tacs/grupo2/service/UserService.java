package com.tacs.grupo2.service;

import com.tacs.grupo2.dto.UserDTO;
import com.tacs.grupo2.dto.UserUpdateDTO;
import com.tacs.grupo2.entity.User;
import com.tacs.grupo2.exceptions.EntityNotFoundException;
import com.tacs.grupo2.mapper.UserMapper;
import com.tacs.grupo2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(userMapper::toDTO).toList();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public UserDTO getLoggedUserDTO() {
        User user =  this.getLoggedUser();
        return userMapper.toDTO(user);
    }
    public User getLoggedUser() {
        return this.userRepository.findById(this.getLoggedUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
    public Long getLoggedUserId() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }
    public UserDTO updateUser(UserUpdateDTO userUpdate) {
        User user = this.getLoggedUser();
        user.updateUser(userUpdate);
        this.userRepository.save(user);
        return userMapper.toDTO(user);
    }
}
