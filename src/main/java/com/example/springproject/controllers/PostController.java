package com.example.springproject.controllers;

import com.example.springproject.models.Post;
import com.example.springproject.models.User;
import com.example.springproject.services.PostService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PostController {
    private final PostService postService;


    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts/{userId}")
    public Post createPost(@RequestBody Post post,
                           @PathVariable Long userId){
        Optional<User> byid = postService.findByid(userId);
        User user = byid.orElse(null);
        post.setAuthor(user);
        return postService.createPost(post);
    }
}
