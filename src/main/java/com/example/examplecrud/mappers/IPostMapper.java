package com.example.examplecrud.mappers;

import com.example.examplecrud.dtos.PostDto;
import com.example.examplecrud.entities.Post;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {ICommentMapper.class})
public interface IPostMapper {
    Post toEntity(PostDto postDto);

    @AfterMapping
    default void linkComments(@MappingTarget Post post) {
        post.getComments().forEach(comment -> comment.setPost(post));
    }

    PostDto toDto(Post post);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Post partialUpdate(PostDto postDto, @MappingTarget Post post);
}