package com.example.springproject.mappers.impl;

import com.example.springproject.mappers.Mapper;
import com.example.springproject.models.DTOs.UserDTO;
import com.example.springproject.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UserMapper implements Mapper<User, UserDTO> {

    private ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTO mapTo(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public User mapfrom(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);

    }
}
