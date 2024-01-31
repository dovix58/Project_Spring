package com.example.springproject.controllers;

import com.example.springproject.models.DTOs.PostDTO;
import com.example.springproject.models.Post;
import com.example.springproject.services.PhotoService;
import com.example.springproject.services.PostService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/users/{userId}/posts/{postId}/photos")
public class PhotoController {
    private final PhotoService photoService;

    private final PostService postService;

    public PhotoController(PhotoService photoService, PostService postService) {
        this.photoService = photoService;
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Long> uploadPhoto(@RequestParam MultipartFile photo, @PathVariable Long postId, @PathVariable Long userId) throws Exception{

            Optional<Post> post = postService.findById(postId);
            if(post.isEmpty() || post.get().getAuthor().getId() != userId){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        return new ResponseEntity<>(photoService.save(photo.getBytes(), photo.getOriginalFilename(), post.get()), HttpStatus.CREATED);
    }

    @GetMapping("/{photoId}")
    public FileSystemResource getImage(@PathVariable Long photoId) throws Exception {
        return photoService.find(photoId);
    }
    @DeleteMapping("/{photoId}")
    public ResponseEntity<PostDTO> deletePhoto(@PathVariable Long photoId) {
        photoService.delete(photoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
