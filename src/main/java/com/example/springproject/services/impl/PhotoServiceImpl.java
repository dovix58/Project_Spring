package com.example.springproject.services.impl;

import com.example.springproject.models.DTOs.Response.PhotoThumbnail;
import com.example.springproject.models.Photo;
import com.example.springproject.models.Post;
import com.example.springproject.repositories.PhotoDbRepo;
import com.example.springproject.repositories.PhotoSystemRepo;
import com.example.springproject.services.PhotoService;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {
    private final PhotoSystemRepo photoSystemRepo;

    private final PhotoDbRepo photoDbRepo;

    public PhotoServiceImpl(PhotoSystemRepo photoSystemRepo, PhotoDbRepo photoDbRepo) {
        this.photoSystemRepo = photoSystemRepo;
        this.photoDbRepo = photoDbRepo;
    }

//TODO naudot optionalus, nes geriau


    @Override
    public Long save(byte[] bytes, String imageName, Post post) throws Exception {
        String location = photoSystemRepo.save(bytes, imageName);
        return photoDbRepo.save(new Photo(imageName, location, post)).getId();
    }

    @Override
    public FileSystemResource find(Long imageId) {
        Photo photo = photoDbRepo.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return photoSystemRepo.findInFileSystem(photo.getLocation());
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
                    .size(640, 480)            // Set desired thumbnail size
                    .outputFormat("jpg")
                    .outputQuality(0.6)// Set format to jpg
                    .toOutputStream(baos);     // Write thumbnail image data to ByteArrayOutputStream

            byte[] thumbnailBytes = baos.toByteArray(); // Convert output stream to byte array
            photoThumbnail.setBytes(thumbnailBytes);    // Set the byte array in PhotoThumbnail
        }

        return photoThumbnail;
    }

}
