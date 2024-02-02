package com.example.springproject.controllers;

import com.example.springproject.models.DTOs.Request.UserRequestDTO;
import com.example.springproject.models.DTOs.Response.UserResponseDTO;
import com.example.springproject.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserContoller {

    private final UserService userService;

    public UserContoller(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO){
        return new ResponseEntity<>(userService.createUser(userRequestDTO), HttpStatus.CREATED);
    }

    @PostMapping("/a")
    public UserResponseDTO createUsera(@RequestBody UserRequestDTO userRequestDTO){
        return userService.createUser(userRequestDTO);
    }
    int x(int x){
        return x;
    }
    @GetMapping()
    public ResponseEntity<List<UserResponseDTO>> listUsers(){
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO){

        Optional<UserResponseDTO> updatedUser = userService.updateUser(id, userRequestDTO);
        if(updatedUser.isPresent()){
            return new ResponseEntity<>(updatedUser.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<UserRequestDTO> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
