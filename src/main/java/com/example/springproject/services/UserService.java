package com.example.springproject.services;

import com.example.springproject.models.DTOs.Request.UserRequest;
import com.example.springproject.models.DTOs.Response.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserResponse createUser(UserRequest user);

    void deleteUser(Long id);

    List<UserResponse> getAll();


    Optional<UserResponse> updateUser(Long id, UserRequest userRequest);
}
