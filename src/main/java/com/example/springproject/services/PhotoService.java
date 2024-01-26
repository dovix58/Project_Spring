package com.example.springproject.services;

import com.example.springproject.models.Post;
import org.springframework.core.io.FileSystemResource;

import java.util.concurrent.ExecutionException;

public interface PhotoService {
    Long save(byte[] bytes, String imageName, Post post) throws Exception;

    FileSystemResource find(Long imageId);
}
