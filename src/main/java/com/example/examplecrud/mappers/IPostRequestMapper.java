package com.example.examplecrud.mappers;

import com.example.examplecrud.dtos.PostRequestDto;
import com.example.examplecrud.entities.Post;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IPostRequestMapper {
    Post toEntity(PostRequestDto postRequestDto);

    PostRequestDto toDto(Post post);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Post partialUpdate(PostRequestDto postRequestDto, @MappingTarget Post post);
}