package com.example.springproject.services;

import com.example.springproject.models.DTOs.Request.PostRequestDTO;
import com.example.springproject.models.DTOs.Response.PostResponseDTO;
import com.example.springproject.models.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Optional<PostResponseDTO> createPost(PostRequestDTO postRequestDTO, Long userId);

    void deletePost(Long id);


    List<PostResponseDTO> getPostsByUser(Long userId);

    Optional<Post> findById(Long postId);

    boolean isExists(Long postId);

    PostResponseDTO updatePost(Long postId, PostRequestDTO postRequestDTO);
}
