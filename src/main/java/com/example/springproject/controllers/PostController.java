package com.example.springproject.controllers;


import com.example.springproject.models.DTOs.Request.PostRequestDTO;
import com.example.springproject.models.DTOs.Response.PostResponseDTO;
import com.example.springproject.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

//    TODO Rest error adviser.
    @PostMapping()
    public PostResponseDTO createPost(@RequestBody PostRequestDTO postRequestDTO, @PathVariable Long userId){
        var post = postService.createPost(postRequestDTO, userId);
        return post.orElseThrow();
    }
    @GetMapping
    public List<PostResponseDTO> listPosts(@PathVariable Long userId){

        return postService.getPostsByUser(userId);
    }
//TODO pasiuziuret, siunciat postDTO ir ida gali siust.
//    @PutMapping("/{postId}")
//    public ResponseEntity<PostResponseDTO> updatePost(@PathVariable Long postId, @RequestBody PostResponseDTO postResponseDTO){
//        if(!postService.isExists(postId)){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        Post postToUpdate = postService.findById(postId).orElse(null);
//        Post updatedPost = postMapper.mapfrom(postResponseDTO);
//       postToUpdate.setTitle(updatedPost.getTitle());
//       postToUpdate.setPhotos(updatedPost.getPhotos());
//       postService.createPost(postToUpdate);
//
//        return new ResponseEntity<>(postMapper.mapTo(postToUpdate), HttpStatus.OK);
//
//
//
//    }
    @DeleteMapping("/{postId}")
    public ResponseEntity<PostResponseDTO> deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
