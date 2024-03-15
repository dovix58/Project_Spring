package com.example.springproject.models.DTOs.Response;

import com.example.springproject.models.Photo;
import com.example.springproject.models.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDTO {
    private Long id;

    private String title;

    @JsonBackReference
    private User author;

    @JsonManagedReference
    private List<Photo> photos;
}
