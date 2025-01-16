package com.example.springproject.services.impl;

import com.example.springproject.mappers.Requests.PhotoRequestMapper;
import com.example.springproject.models.DTOs.Request.PhotoRequest;
import com.example.springproject.models.DTOs.Response.PhotoThumbnail;
import com.example.springproject.models.Photo;
import com.example.springproject.models.Post;
import com.example.springproject.repositories.PhotoDbRepo;
import com.example.springproject.repositories.PhotoSystemRepo;
import com.example.springproject.services.PhotoService;
import com.example.springproject.services.PostService;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PhotoServiceImpl implements PhotoService {
    private final PhotoSystemRepo photoSystemRepo;

    private final PhotoDbRepo photoDbRepo;

    private final PhotoRequestMapper photoRequestMapper;

    private final PostService postService;

    public PhotoServiceImpl(PhotoSystemRepo photoSystemRepo, PhotoDbRepo photoDbRepo, PhotoRequestMapper photoRequestMapper, PostService postService) {
        this.photoSystemRepo = photoSystemRepo;
        this.photoDbRepo = photoDbRepo;
        this.photoRequestMapper = photoRequestMapper;
        this.postService = postService;
    }

//TODO naudot optionalus, nes geriau


    @Override
    public Photo uploadPhoto(PhotoRequest photoRequest, Long postId, Long userId) throws Exception {
        Photo photo = photoRequestMapper.photoRequestDTOToPhoto(photoRequest);
        Optional<Post> post = postService.findById(postId);
        if(post.isEmpty() || !Objects.equals(post.get().getAuthor().getId(), userId)){
           throw new IllegalArgumentException();
        }

        String location = photoSystemRepo.save(photoRequest.getBytes(), photo.getName());

        return photoDbRepo.save(new Photo(photo.getName(), location, photo.getLocation(), photo.getDate(), post.get()));
    }

    @Override
    public Optional<FileSystemResource> find(Long imageId) {
         return photoDbRepo.findById(imageId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
                .map((photo1) -> photoSystemRepo.findInFileSystem(photo1.getLocation()));

    }

//    @Override
//    public List<FileSystemResource> findAllPhotos(Long postId) {
//        List<Photo> photos = photoDbRepo.getPhotosByPost_Id(postId);
//
//    }

    @Override
    public void delete(Long photoId) {
        Photo photo = photoDbRepo.findById(photoId).orElse(null);

        if(photo != null){
            photoSystemRepo.deleteFromSystem(photo.getLocation());
        }
        photoDbRepo.deleteById(photoId);


    }

    @Override
    public List<Long> findPhotoIdsByPost(Long postId) {
        return photoDbRepo.findPhotoIdsByPost(postId);
    }

    @Override
    public PhotoThumbnail getPhotoThumbnail(Long photoId) throws IOException {
        var photo = photoDbRepo.findById(photoId).orElseThrow();
        var resource = photoSystemRepo.findInFileSystem(photo.getLocation());

        PhotoThumbnail photoThumbnail = new PhotoThumbnail();
        photoThumbnail.setId(photo.getId());
        photoThumbnail.setName(photo.getName());

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Thumbnails.of(resource.getFile())  // Use the file found in the filesystem
                    .size(20, 50)           //TODO properties
                    .outputFormat("jpg")
                    .outputQuality(0.6)
                    .toOutputStream(baos);     // Write thumbnail image data to ByteArrayOutputStream

            byte[] thumbnailBytes = baos.toByteArray(); // Convert output stream to byte array
            photoThumbnail.setBytes(thumbnailBytes);    // Set the byte array in PhotoThumbnail
        }

        return photoThumbnail;
    }

}
