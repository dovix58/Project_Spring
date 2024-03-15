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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepo postRepo;

    private final UserRepo userRepo;

    private final PostResponseMapper postResponseMapper;

    private final PostRequestMapper postRequestMapper;

    public PostServiceImpl(PostRepo postRepo, UserRepo userRepo, PostResponseMapper postResponseMapper, PostRequestMapper postRequestMapper) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
        this.postResponseMapper = postResponseMapper;
        this.postRequestMapper = postRequestMapper;
    }

    @Override
    public Optional<PostResponseDTO> createPost(PostRequestDTO postRequestDTO, Long userId) {
        Post postToCreate = postRequestMapper.postRequestDTOToPost(postRequestDTO);
        postToCreate.setAuthor(userRepo.findById(userId).orElseThrow());
        return Optional.of(postResponseMapper.postToPostResponseDTO(postRepo.save(postToCreate)));
    }

    @Override
    public void deletePost(Long id) {
        postRepo.deleteById(id);
    }



    @Override
    public List<PostResponseDTO> getPostsByUser(Long userId) {
        return postRepo.getPostByAuthor_Id(userId)
                .stream()
                .map(postResponseMapper::postToPostResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Post> findById(Long postId) {
        return postRepo.findById(postId);
    }

    @Override
    public boolean isExists(Long postId) {
        return postRepo.existsById(postId);
    }

    @Override
    public PostResponseDTO updatePost(Long postId, PostRequestDTO postRequestDTO) {
        var postToUpdate = findById(postId).orElseThrow();
        postToUpdate.setTitle(postRequestDTO.getTitle());
        postRepo.save(postToUpdate);
        return postResponseMapper.postToPostResponseDTO(postToUpdate);
    }


}
