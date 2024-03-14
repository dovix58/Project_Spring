package com.example.springproject.mappers.Responses;

import com.example.springproject.models.DTOs.Response.UserResponseDTO;
import com.example.springproject.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)

public interface UserResponseMapper {

    UserResponseDTO userToResponseDTO(User user);

    User userResponseDTOToUser(UserResponseDTO userResponseDTO);



}
