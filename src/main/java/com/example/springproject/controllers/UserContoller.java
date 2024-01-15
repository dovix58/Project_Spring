package com.example.springproject.controllers;

import com.example.springproject.mappers.impl.UserMapper;
import com.example.springproject.models.DTOs.UserDTO;
import com.example.springproject.models.User;
import com.example.springproject.services.UserService;
import com.example.springproject.services.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserContoller {

    private UserService userService;

    private UserMapper userMapper;

    public UserContoller(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping(path = "/users")
    public UserDTO createUser(@RequestBody UserDTO userDTO){
        User user = userMapper.mapfrom(userDTO);
        User savedUser = userService.createUser(user);
        return userMapper.mapTo(savedUser);
    }
}
