package com.example.springproject.controllers;

import com.example.springproject.mappers.impl.PostMapper;
import com.example.springproject.models.DTOs.PostDTO;
import com.example.springproject.models.Post;
import com.example.springproject.models.User;
import com.example.springproject.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users/{userId}/posts")
public class PostController {
    private final PostService postService;
    private final PostMapper postMapper;


    public PostController(PostService postService, PostMapper postMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
    }



//    TODO galima grazint dto o jeigu ner userio fatalas.
//    TODO Rest error adviser.
    @PostMapping()
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postdto,
                           @PathVariable Long userId){
        User user = postService.findUserById(userId).orElse(null);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
//        TODO optional grazint per resta pa
        Post post = postMapper.mapfrom(postdto);
        post.setAuthor(user);
        Post savedPost = postService.createPost(post);
        return new ResponseEntity<>(postMapper.mapTo(savedPost), HttpStatus.CREATED) ;

    }
    @GetMapping
    public List<PostDTO> listPosts(@PathVariable Long userId){
        List<Post> posts = postService.findall();
        return posts.stream()
                .filter(x -> Objects.equals(x.getAuthor().getId(), userId))
                .map(postMapper::mapTo)
                .collect(Collectors.toList());
    }
//TODO pasiuziuret, siunciat postDTO ir ida gali siust.
    @PutMapping("/{postId}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable Long postId, @RequestBody PostDTO postDTO){
        if(!postService.isExists(postId)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Post postToUpdate = postService.findById(postId).orElse(null);
        Post updatedPost = postMapper.mapfrom(postDTO);
       postToUpdate.setTitle(updatedPost.getTitle());
       postToUpdate.setPhotos(updatedPost.getPhotos());
       postService.createPost(postToUpdate);

        return new ResponseEntity<>(postMapper.mapTo(postToUpdate), HttpStatus.OK);



    }
    @DeleteMapping("/{postId}")
    public ResponseEntity<PostDTO> deletePost(@PathVariable Long postId){
        if(!postService.isExists(postId)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postService.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
