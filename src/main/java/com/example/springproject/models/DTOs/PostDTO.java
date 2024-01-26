package com.example.springproject.models.DTOs;

import com.example.springproject.models.Photo;
import com.example.springproject.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private Long id;

    private String title;

    @JsonIgnore
    private UserDTO author;


    private List<PhotoDTO> photos;
}
