package com.example.examplecrud.services.impl;

import com.example.examplecrud.dtos.CommentRequestDto;
import com.example.examplecrud.dtos.CommentResponseDto;
import com.example.examplecrud.entities.Comment;
import com.example.examplecrud.mappers.ICommentRequestMapper;
import com.example.examplecrud.mappers.ICommentResponseMapper;
import com.example.examplecrud.repositories.CommentRepository;
import com.example.examplecrud.services.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    CommentRepository commentRepository;
    
    @Autowired
    ICommentRequestMapper commentRequestMapper;
    
    @Autowired
    ICommentResponseMapper commentResponseMapper;
    
    @Override
    public List<CommentResponseDto> findAllComments() {
        List<Comment> comments = commentRepository.findAll().stream().toList();
        if (comments.isEmpty()) {
            return Collections.emptyList();
        }

        return commentRepository.findAll()
                .stream().map(commentResponseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CommentResponseDto getComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id.toString()));
        return commentResponseMapper.toDto(comment);
    }

    @Override
    public CommentResponseDto addComment(CommentRequestDto commentRequestDto) {
        Comment comment = commentRequestMapper.toEntity(commentRequestDto);
        Comment savedComment = commentRepository.save(comment);
        return commentResponseMapper.toDto(savedComment);
    }

    @Override
    public CommentResponseDto updateComment(Long id, CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Comment not found with id: " + id.toString()));
        Comment updatedComment = commentRequestMapper.partialUpdate(commentRequestDto, comment);
        Comment savedComment = commentRepository.save(updatedComment);
        return commentResponseMapper.toDto(savedComment);
    }

    @Override
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id.toString()));
        commentRepository.delete(comment);
    }
}
