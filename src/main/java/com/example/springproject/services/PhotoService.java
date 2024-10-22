package com.example.springproject.services;

import com.example.springproject.models.Post;
import org.springframework.core.io.FileSystemResource;

import java.util.List;

public interface PhotoService {
    Long save(byte[] bytes, String imageName, Post post) throws Exception;

    FileSystemResource find(Long imageId);

    List<FileSystemResource> findAllPhotos(Long imageId);

    void delete(Long photoId);
}
