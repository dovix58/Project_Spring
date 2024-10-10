package com.example.springproject.models.DTOs.Response;

import com.example.springproject.models.Photo;
import com.example.springproject.models.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

    private String author;

}
