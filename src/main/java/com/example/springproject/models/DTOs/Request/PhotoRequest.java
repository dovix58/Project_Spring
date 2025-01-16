package com.example.springproject.models.DTOs.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoRequest {

    private byte[] bytes;
    private String name;
    private String description;
    private LocalDate date;
}
