package com.example.springproject.mappers.impl;

import com.example.springproject.mappers.Mapper;
import com.example.springproject.models.DTOs.PostDTO;
import com.example.springproject.models.Post;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PostMapper implements Mapper<Post, PostDTO> {

    private ModelMapper modelMapper;

    public PostMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDTO mapTo(Post post) {
        return modelMapper.map(post, PostDTO.class);
    }

    @Override
    public Post mapfrom(PostDTO postDTO) {
        return modelMapper.map(postDTO, Post.class);
    }
}
