package com.example.springproject.models.DTOs;


import com.example.springproject.models.DTOs.Response.PostResponseDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoDTO {

    private Long id;
    private String name;

    @JsonIgnore
    private PostResponseDTO post;
}
