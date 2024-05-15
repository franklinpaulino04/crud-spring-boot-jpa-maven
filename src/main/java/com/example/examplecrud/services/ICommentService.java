package com.example.examplecrud.services;

import com.example.examplecrud.dtos.CommentRequestDto;
import com.example.examplecrud.dtos.CommentResponseDto;
import com.example.examplecrud.entities.Comment;
import com.example.examplecrud.entities.Post;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICommentService {
    Page<Comment> findAllComments(int pageNo, int pageSize, String sortBy, String sortDirection);
    CommentResponseDto getComment(Long id);
    CommentResponseDto addComment(CommentRequestDto commentRequestDto);
    CommentResponseDto updateComment(Long id, CommentRequestDto commentRequestDto);
    void deleteComment(Long id);
}
