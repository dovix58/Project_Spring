package com.example.springproject.mappers.Requests;

import com.example.springproject.models.DTOs.Request.PostRequest;
import com.example.springproject.models.Post;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)

public interface PostRequestMapper {

    Post postRequestDTOToPost(PostRequest postRequest);
}
