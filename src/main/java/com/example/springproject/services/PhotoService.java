package com.example.springproject.services;

import com.example.springproject.models.DTOs.Response.PhotoThumbnail;
import com.example.springproject.models.Post;
import org.springframework.core.io.FileSystemResource;

import java.io.IOException;
import java.util.List;

public interface PhotoService {
    Long save(byte[] bytes, String imageName, Post post) throws Exception;

    FileSystemResource find(Long imageId);

//    List<FileSystemResource> findAllPhotos(Long imageId);

    void delete(Long photoId);

    List<Long> findPhotoIdsByPost(Long postId);

    PhotoThumbnail getPhotoThumbnail(Long photoId) throws IOException;
}
