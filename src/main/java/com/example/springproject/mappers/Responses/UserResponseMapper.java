package com.example.springproject.mappers.Responses;

import com.example.springproject.models.DTOs.Response.UserResponseDTO;
import com.example.springproject.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserResponseMapper {
    UserResponseMapper INSTANCE = Mappers.getMapper(UserResponseMapper.class);


    UserResponseDTO userToResponseDTO(User user);

    User userResponseDTOToUser(UserResponseDTO userResponseDTO);



}
