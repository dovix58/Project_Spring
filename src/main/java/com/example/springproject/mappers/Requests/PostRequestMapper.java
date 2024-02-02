package com.example.springproject.mappers.Requests;

import com.example.springproject.models.DTOs.Request.PostRequestDTO;
import com.example.springproject.models.Post;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostRequestMapper {
    PostRequestMapper INSTANCE = Mappers.getMapper(PostRequestMapper.class);

    Post postRequestDTOToPost(PostRequestDTO requestDTO);

    PostRequestDTO postToPostRequestDTO(Post post);
}
