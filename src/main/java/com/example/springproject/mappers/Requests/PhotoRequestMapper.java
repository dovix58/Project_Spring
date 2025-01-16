package com.example.springproject.mappers.Requests;

import com.example.springproject.models.DTOs.Request.PhotoRequest;
import com.example.springproject.models.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PhotoRequestMapper {
    Photo photoRequestDTOToPhoto(PhotoRequest photoRequest);
}
