package com.example.examplecrud.mappers;

import com.example.examplecrud.dtos.CommentRequestDto;
import com.example.examplecrud.entities.Comment;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ICommentRequestMapper {
    Comment toEntity(CommentRequestDto commentRequestDto);

    CommentRequestDto toDto(Comment comment);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Comment partialUpdate(CommentRequestDto commentRequestDto, @MappingTarget Comment comment);
}