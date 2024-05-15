package com.example.examplecrud.services.impl;

import com.example.examplecrud.dtos.CommentDto;
import com.example.examplecrud.dtos.CommentRequestDto;
import com.example.examplecrud.dtos.CommentResponseDto;
import com.example.examplecrud.entities.Comment;
import com.example.examplecrud.entities.User;
import com.example.examplecrud.mappers.ICommentMapper;
import com.example.examplecrud.mappers.ICommentRequestMapper;
import com.example.examplecrud.mappers.ICommentResponseMapper;
import com.example.examplecrud.repositories.CommentRepository;
import com.example.examplecrud.services.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    @Autowired
    private ICommentMapper iCommentMapper;

    @Override
    public Page<CommentDto> findAllComments(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return commentRepository.findAll(pageable).map(iCommentMapper::toDto);
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
