package com.example.springproject.models.DTOs.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoThumbnail {

    private Long id;
    private String name;

    private byte[] bytes;
}
