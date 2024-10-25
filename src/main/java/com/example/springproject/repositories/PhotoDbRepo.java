package com.example.springproject.repositories;

import com.example.springproject.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoDbRepo extends JpaRepository<Photo, Long> {

//    List<Photo> getPhotosByPost_Id(Long postId);

    @Query("SELECT p.id FROM Photo p WHERE p.post.id = :postId")
    List<Long> findPhotoIdsByPost(@Param("postId") Long postId);

}
