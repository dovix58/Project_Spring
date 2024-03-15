package com.example.springproject.controllers;


import com.example.springproject.models.DTOs.Request.PostRequestDTO;
import com.example.springproject.models.DTOs.Response.PostResponseDTO;
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
    public PostResponseDTO createPost(@RequestBody PostRequestDTO postRequestDTO, @PathVariable Long userId){
        var post = postService.createPost(postRequestDTO, userId);
        return post.orElseThrow();
    }
    @GetMapping
    public List<PostResponseDTO> listPosts(@PathVariable Long userId){

        return postService.getPostsByUser(userId);
    }
    @PutMapping("/{postId}")
    public PostResponseDTO updatePost(@PathVariable Long postId, @RequestBody PostRequestDTO postRequestDTO){
//        if(!postService.isExists(postId)){
//             throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "post not found"
//            );
//        }
//
//        var postToUpdate = postService.findById(postId);
//        var updatedPost = postMapper.mapfrom(postResponseDTO);
//       postToUpdate.setTitle(updatedPost.getTitle());
//       postToUpdate.setPhotos(updatedPost.getPhotos());
//       postService.createPost(postToUpdate);
//
//        return postMapper.mapTo(postToUpdate);

        return postService.updatePost(postId, postRequestDTO);

    }
    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
    }
}
