package com.example.springproject.mappers.Responses;

import com.example.springproject.models.DTOs.Response.PostResponseDTO;
import com.example.springproject.models.Post;
import com.example.springproject.models.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PostResponseMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "post.id") // Map the id field
    @Mapping(target = "title", source = "post.title") // Map the title field
    @Mapping(target = "author", source = "post.author.username") // Map the author's username to author in DTO
    PostResponseDTO postToPostResponseDTO(Post post);

}