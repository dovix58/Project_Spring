package com.example.springproject.services;

import com.example.springproject.models.DTOs.Request.UserRequestDTO;
import com.example.springproject.models.DTOs.Response.UserResponseDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO user);

    void deleteUser(Long id);

    List<UserResponseDTO> getAll();


    Optional<UserResponseDTO> updateUser(Long id, UserRequestDTO userRequestDTO);
}
