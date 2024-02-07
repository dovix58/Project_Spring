package com.example.springproject.mappers.Responses;

import com.example.springproject.models.DTOs.Response.PostResponseDTO;
import com.example.springproject.models.Post;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PostResponseMapper {
    PostResponseDTO postToPostResponseDTO(Post Post);
}
