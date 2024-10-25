package com.example.springproject.mappers.Responses;

import com.example.springproject.models.DTOs.Response.PhotoThumbnail;
import com.example.springproject.models.Photo;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PhotoThumbnailMapper {
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "photo.id")
    PhotoThumbnail photoToPhotoThumbnail(Photo photo);
}
