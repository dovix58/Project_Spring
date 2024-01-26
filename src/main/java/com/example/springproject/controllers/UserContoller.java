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
@RequestMapping("/users")
public class UserContoller {

    private final UserService userService;

    private final UserMapper userMapper;

    public UserContoller(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping()
    public UserDTO createUser(@RequestBody UserDTO userDTO){
        User user = userMapper.mapfrom(userDTO);
        User savedUser = userService.createUser(user);
        return userMapper.mapTo(savedUser);
    }

    @GetMapping()
    public List<UserDTO> listUsers(){
        List<User> users = userService.findall();
        return users.stream()
                .map(userMapper::mapTo)
                .collect(Collectors.toList());
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO){
        if(!userService.isExist(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User userToUpdate = userService.findById(id).orElse(null);
        User updatedUser = userMapper.mapfrom(userDTO);
        userToUpdate.setPosts(updatedUser.getPosts());
        userToUpdate.setUsername(updatedUser.getUsername());
        userService.createUser(userToUpdate);

        return new ResponseEntity<>(userMapper.mapTo(userToUpdate), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
