package com.example.examplecrud.mappers;

import com.example.examplecrud.dtos.CommentResponseDto;
import com.example.examplecrud.entities.Comment;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ICommentResponseMapper {
    Comment toEntity(CommentResponseDto commentResponseDto);

    CommentResponseDto toDto(Comment comment);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Comment partialUpdate(CommentResponseDto commentResponseDto, @MappingTarget Comment comment);
}