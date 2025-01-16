package com.example.springproject.controllers;

import com.example.springproject.models.DTOs.Request.PhotoRequest;
import com.example.springproject.models.DTOs.Response.PhotoThumbnail;
import com.example.springproject.models.Photo;
import com.example.springproject.models.Post;
import com.example.springproject.services.PhotoService;
import com.example.springproject.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public ResponseEntity<Photo> uploadPhoto(@RequestParam PhotoRequest photoRequest, @PathVariable Long postId, @PathVariable Long userId) throws Exception{

            return new ResponseEntity<>(photoService.uploadPhoto(photoRequest,postId, userId), HttpStatus.CREATED);

//        Optional<Post> post = postService.findById(postId);
//            if(post.isEmpty() || !Objects.equals(post.get().getAuthor().getId(), userId)){
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//        return new ResponseEntity<>(photoService.uploadPhoto(photo.getBytes(), photo.getOriginalFilename(), post.get()), HttpStatus.CREATED);
    }

//    @GetMapping("/{photoId}/image")
//    public ResponseEntity<FileSystemResource> getImage(@PathVariable Long photoId) {
//        try {
//            Optional<FileSystemResource> image = Optional.of(photoService.find(photoId).orElseThrow());
//
//
//            String contentType = Files.probeContentType(image.getFile().toPath());  //TODO contentTYpe post metu sukuriamas ir issaugai duomeny bazei.
//            return ResponseEntity.ok()
//                    .contentType(MediaType.parseMediaType(contentType))     //TODO image.contentTYpe bus
//                    .body(image);
//        } catch (Exception e) { //TODO arba gaudai tipizuotus errorus arba negaudau isvis.
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
    @GetMapping("/{photoId}/thumbnail")
    public ResponseEntity<PhotoThumbnail> getThumbnail(@PathVariable Long photoId) throws IOException {
        return ResponseEntity.ok(photoService.getPhotoThumbnail(photoId)); //TODO turi but padarytas thumbnailas kai gauna photo.
    }
    @GetMapping()
    public ResponseEntity<List<Long>> getAllPhotoIds(@PathVariable Long postId){
        return ResponseEntity.ok(photoService.findPhotoIdsByPost(postId));
    }

    @DeleteMapping("/{photoId}")
    public void deletePhoto(@PathVariable Long photoId) {
        photoService.delete(photoId);
    }
}