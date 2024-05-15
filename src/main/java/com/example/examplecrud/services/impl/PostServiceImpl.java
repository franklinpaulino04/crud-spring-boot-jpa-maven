package com.example.examplecrud.services.impl;

import com.example.examplecrud.dtos.PostResponseDto;
import com.example.examplecrud.dtos.PostRequestDto;
import com.example.examplecrud.entities.Comment;
import com.example.examplecrud.entities.Post;
import com.example.examplecrud.mappers.IPostRequestMapper;
import com.example.examplecrud.mappers.IPostResponseMapper;
import com.example.examplecrud.repositories.PostRepository;
import com.example.examplecrud.services.IPostService;
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
public class PostServiceImpl implements IPostService {

    @Autowired
    PostRepository postRepository;
    
    @Autowired
    IPostRequestMapper postRequestMapper;
    
    @Autowired
    IPostResponseMapper postResponseMapper;
    
    @Override
    public Page<Post> findAllPosts(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return postRepository.findAll(pageable);
    }

    @Override
    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id.toString()));
        return postResponseMapper.toDto(post);
    }

    @Override
    public PostResponseDto addPost(PostRequestDto postRequestDto) {
        Post post = postRequestMapper.toEntity(postRequestDto);
        Post savedPost = postRepository.save(post);
        return postResponseMapper.toDto(savedPost);
    }

    @Override
    public PostResponseDto updatePost(Long id, PostRequestDto postRequestDto) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found with id: " + id.toString()));
        Post updatedPost = postRequestMapper.partialUpdate(postRequestDto, post);
        Post savedPost = postRepository.save(updatedPost);
        return postResponseMapper.toDto(savedPost);
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id.toString()));
        postRepository.delete(post);
    }
}
