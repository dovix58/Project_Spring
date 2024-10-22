package com.example.springproject.services;

import com.example.springproject.models.DTOs.Request.PostRequest;
import com.example.springproject.models.DTOs.Response.PostResponse;
import com.example.springproject.models.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Optional<PostResponse> createPost(PostRequest postRequest, Long userId);

    void deletePost(Long id);


    List<PostResponse> getPostsByUser(Long userId);

    Optional<Post> findById(Long postId);

    boolean isExists(Long postId);

    PostResponse updatePost(Long postId, PostRequest postRequest);
}
