package com.example.springproject.controllers;

import com.example.springproject.models.Post;
import com.example.springproject.services.PhotoService;
import com.example.springproject.services.PostService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
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
            if(post.isEmpty() || !Objects.equals(post.get().getAuthor().getId(), userId)){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        return new ResponseEntity<>(photoService.save(photo.getBytes(), photo.getOriginalFilename(), post.get()), HttpStatus.CREATED);
    }

    @GetMapping("/{photoId}")
    public ResponseEntity<FileSystemResource> getImage(@PathVariable Long photoId) {
        try {
            FileSystemResource image = photoService.find(photoId);
            if (!image.exists()) {
                return ResponseEntity.notFound().build();
            }

            String contentType = Files.probeContentType(image.getFile().toPath());
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(image);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping()
    public ResponseEntity<List<FileSystemResource>> getAllPhotos(@PathVariable Long postId){

    }
    @DeleteMapping("/{photoId}")
    public void deletePhoto(@PathVariable Long photoId) {
        photoService.delete(photoId);
    }
}
