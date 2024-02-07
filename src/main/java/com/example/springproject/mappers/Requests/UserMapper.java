package com.example.springproject.mappers.Requests;

import com.example.springproject.models.DTOs.Request.UserRequestDTO;
import com.example.springproject.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User userDTOToUser(UserRequestDTO userRequestDTO);

}
