package com.example.springproject.services.impl;

import com.example.springproject.mappers.Requests.PostRequestMapper;
import com.example.springproject.mappers.Responses.PostResponseMapper;
import com.example.springproject.models.DTOs.Request.PostRequestDTO;
import com.example.springproject.models.DTOs.Response.PostResponseDTO;
import com.example.springproject.models.Post;
import com.example.springproject.repositories.PostRepo;
import com.example.springproject.repositories.UserRepo;
import com.example.springproject.services.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepo postRepo;

    private final UserRepo userRepo;

    public PostServiceImpl(PostRepo postRepo, UserRepo userRepo) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }


    @Override
    public Optional<PostResponseDTO> createPost(PostRequestDTO postRequestDTO, Long userId) {


        if (!userRepo.existsById(userId)) {
            return Optional.empty();
        }
        Post postToCreate = PostRequestMapper.INSTANCE.postRequestDTOToPost(postRequestDTO);
        return Optional.of(PostResponseMapper.INSTANCE.postToPostResponseDTO(postRepo.save(postToCreate)));
    }

    @Override
    public void deletePost(Long id) {
        postRepo.deleteById(id);
    }

    @Override
    public List<PostResponseDTO> getAll(Long userId) {
        return postRepo
                .findAll()
                .stream()
                .filter(x -> Objects.equals(x.getAuthor().getId(), userId))
                .map(PostResponseMapper.INSTANCE::postToPostResponseDTO)
                .collect(Collectors.toList());
    }




}
