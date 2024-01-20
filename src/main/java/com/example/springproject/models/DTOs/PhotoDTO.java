package com.example.springproject.models.DTOs;


import com.example.springproject.models.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoDTO {

    private Long id;

    private PostDTO post;
}
