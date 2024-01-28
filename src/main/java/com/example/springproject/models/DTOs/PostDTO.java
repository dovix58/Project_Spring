package com.example.springproject.models.DTOs;

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

    private Long userID;
//    TODO pazet maperi, kaip veikia, ar pagal name.

    private List<PhotoDTO> photos;
}
