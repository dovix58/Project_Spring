package com.example.springproject.models.DTOs.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    private String username;

    private List<PostRequestDTO> posts;
}
