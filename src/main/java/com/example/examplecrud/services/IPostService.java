package com.example.examplecrud.services;

import com.example.examplecrud.dtos.*;
import com.example.examplecrud.entities.Post;
import com.example.examplecrud.entities.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IPostService {
    Page<PostDto> findAllPosts(int pageNo, int pageSize, String sortBy, String sortDirection);
    PostResponseDto getPost(Long id);
    PostResponseDto addPost(PostRequestDto postRequestDto);
    PostResponseDto updatePost(Long id, PostRequestDto postRequestDto);
    void deletePost(Long id);
}
