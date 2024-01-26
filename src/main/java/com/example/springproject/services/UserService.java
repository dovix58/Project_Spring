package com.example.springproject.services;

import com.example.springproject.models.DTOs.UserDTO;
import com.example.springproject.models.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);

    void deleteUser(Long id);

    List<User> findall();

    Optional<User> findById(Long id);

    boolean isExist(Long id);
}
