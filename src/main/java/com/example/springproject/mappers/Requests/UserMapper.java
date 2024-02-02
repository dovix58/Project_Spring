package com.example.springproject.mappers.Requests;

import com.example.springproject.models.DTOs.Request.UserRequestDTO;
import com.example.springproject.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userDTOToUser(UserRequestDTO userRequestDTO);



}
