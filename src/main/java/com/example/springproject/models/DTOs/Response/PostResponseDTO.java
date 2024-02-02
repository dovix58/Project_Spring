package com.example.springproject.models.DTOs.Response;

import com.example.springproject.models.DTOs.PhotoDTO;
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

    private Long userID;

    private List<PhotoDTO> photos;
}
