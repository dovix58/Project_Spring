package com.example.springproject.services.impl;

import com.example.springproject.models.Post;
import com.example.springproject.models.User;
import com.example.springproject.repositories.PostRepo;
import com.example.springproject.repositories.UserRepo;
import com.example.springproject.services.PostService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepo postRepo;

    private final UserRepo userRepo;

    public PostServiceImpl(PostRepo postRepo, UserRepo userRepo) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Post createPost(Post post) {
        return postRepo.save(post);
    }

    @Override
    public void deletePost(Long id) {
        postRepo.deleteById(id);
    }

    @Override
    public List<Post> findall() {
        return new ArrayList<>(postRepo
                .findAll());
    }

    @Override
    public Optional<User> findByid(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return postRepo.existsById(id);
    }
}
