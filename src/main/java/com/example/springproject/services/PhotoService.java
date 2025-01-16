package com.example.springproject.services;

import com.example.springproject.models.DTOs.Request.PhotoRequest;
import com.example.springproject.models.DTOs.Response.PhotoThumbnail;
import com.example.springproject.models.Photo;
import com.example.springproject.models.Post;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface PhotoService {
    Photo uploadPhoto(PhotoRequest photoRequest, Long postId, Long userId) throws Exception;

    Optional<FileSystemResource> find(Long imageId);

//    List<FileSystemResource> findAllPhotos(Long imageId);

    void delete(Long photoId);

    List<Long> findPhotoIdsByPost(Long postId);

    PhotoThumbnail getPhotoThumbnail(Long photoId) throws IOException;
}
