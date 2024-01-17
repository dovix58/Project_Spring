package com.example.springproject.controllers;

import com.example.springproject.mappers.impl.UserMapper;
import com.example.springproject.models.DTOs.UserDTO;
import com.example.springproject.models.User;
import com.example.springproject.services.UserService;
import com.example.springproject.services.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserContoller {

    private final UserService userService;

    private final UserMapper userMapper;

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

    @GetMapping("/users")
    public List<UserDTO> listUsers(){
        List<User> users = userService.findall();
        return users.stream()
                .map(userMapper::mapTo)
                .collect(Collectors.toList());
    }
    @PutMapping("users/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO){
        if(!userService.isExist(id)){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userDTO.setId(id);
        User user = userMapper.mapfrom(userDTO);
        User updatedUser = userService.createUser(user);
        return new ResponseEntity<>(userMapper.mapTo(updatedUser), HttpStatus.OK);
    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
