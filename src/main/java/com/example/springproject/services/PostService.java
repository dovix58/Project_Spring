package com.example.springproject.services;

import com.example.springproject.models.Post;
import com.example.springproject.models.User;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Post createPost(Post post);

    void deletePost(Long id);

    List<Post> findall();

    Optional<User> findByid(Long id);

    boolean isExists(Long id);
}
