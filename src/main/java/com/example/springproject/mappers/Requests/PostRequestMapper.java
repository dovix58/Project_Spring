package com.example.springproject.mappers.Requests;

import com.example.springproject.models.DTOs.Request.PostRequestDTO;
import com.example.springproject.models.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostRequestMapper {

    Post postRequestDTOToPost(PostRequestDTO requestDTO);

    PostRequestDTO postToPostRequestDTO(Post post);
}
