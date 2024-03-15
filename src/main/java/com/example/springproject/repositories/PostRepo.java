package com.example.springproject.repositories;

import com.example.springproject.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

    List<Post> getPostByAuthor_Id(Long authorId);
}
