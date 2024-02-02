package com.example.springproject.models.DTOs.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private Long id;

    private String username;

    private List<PostResponseDTO> posts;
}
