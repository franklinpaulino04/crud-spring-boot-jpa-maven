package com.example.examplecrud.mappers;

import com.example.examplecrud.dtos.PostResponseDto;
import com.example.examplecrud.entities.Post;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IPostResponseMapper {
    Post toEntity(PostResponseDto postResponseDto);

    @AfterMapping
    default void linkComments(@MappingTarget Post post) {
        post.getComments().forEach(comment -> comment.setPost(post));
    }

    PostResponseDto toDto(Post post);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Post partialUpdate(PostResponseDto postResponseDto, @MappingTarget Post post);
}