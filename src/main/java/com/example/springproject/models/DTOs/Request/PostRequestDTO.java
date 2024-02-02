package com.example.springproject.models.DTOs.Request;

import com.example.springproject.models.Photo;

import java.util.List;

public class PostRequestDTO {

    private String title;
//    TODO ISIDETI  i entity created date ir last updated date.

    private Long userID;

    private List<Photo> photos;
}
