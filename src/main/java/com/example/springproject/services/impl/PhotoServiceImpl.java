package com.example.springproject.services.impl;

import com.example.springproject.models.Photo;
import com.example.springproject.models.Post;
import com.example.springproject.repositories.PhotoDbRepo;
import com.example.springproject.repositories.PhotoSystemRepo;
import com.example.springproject.services.PhotoService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    @Override
    public List<FileSystemResource> findAllPhotos(Long postId) {
        List<Photo> photos = photoDbRepo.getPhotosByPost_Id(postId);

    }

    @Override
    public void delete(Long photoId) {
        Photo photo = photoDbRepo.findById(photoId).orElse(null);

        if(photo != null){
            photoSystemRepo.deleteFromSystem(photo.getLocation());
        }
        photoDbRepo.deleteById(photoId);


    }

}
