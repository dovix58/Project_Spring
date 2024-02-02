package com.example.springproject.mappers.Responses;

import com.example.springproject.models.DTOs.Response.PostResponseDTO;
import com.example.springproject.models.Post;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostResponseMapper {
    PostResponseMapper INSTANCE = Mappers.getMapper(PostResponseMapper.class);

    PostResponseDTO postToPostResponseDTO(Post Post);
}
