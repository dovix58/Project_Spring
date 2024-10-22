package com.example.springproject.repositories;

import com.example.springproject.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoDbRepo extends JpaRepository<Photo, Long> {

    List<Photo> getPhotosByPost_Id(Long postId);

}
