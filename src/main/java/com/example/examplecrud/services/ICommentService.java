package com.example.examplecrud.services;

import com.example.examplecrud.dtos.CommentRequestDto;
import com.example.examplecrud.dtos.CommentResponseDto;

import java.util.List;

public interface ICommentService {
    List<CommentResponseDto> findAllComments();
    CommentResponseDto getComment(Long id);
    CommentResponseDto addComment(CommentRequestDto commentRequestDto);
    CommentResponseDto updateComment(Long id, CommentRequestDto commentRequestDto);
    void deleteComment(Long id);
}
