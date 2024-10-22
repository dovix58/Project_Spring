package com.example.springproject.mappers.Responses;

import com.example.springproject.models.DTOs.Response.UserResponse;
import com.example.springproject.models.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)

public interface UserResponseMapper {
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "user.id") // Map the id field
    @Mapping(target = "username", source = "user.username") // Map the id field
    UserResponse userToResponseDTO(User user);

}
