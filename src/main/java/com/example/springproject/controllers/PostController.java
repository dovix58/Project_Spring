package com.example.springproject.controllers;


import com.example.springproject.models.DTOs.Request.PostRequest;
import com.example.springproject.models.DTOs.Response.PostResponse;
import com.example.springproject.services.PostService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/posts")
public class PostController {
    private final PostService postService;


    public PostController(PostService postService) {
        this.postService = postService;
    }


    @PostMapping()
    public PostResponse createPost(@RequestBody PostRequest postRequest, @PathVariable Long userId){
        var post = postService.createPost(postRequest, userId);
        return post.orElseThrow();
    }
    @GetMapping
    public List<PostResponse> listPosts(@PathVariable Long userId){

        return postService.getPostsByUser(userId);
    }
    @PutMapping("/{postId}")
    public PostResponse updatePost(@PathVariable Long postId, @RequestBody PostRequest postRequest){

        return postService.updatePost(postId, postRequest);

    }
    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
    }
}
