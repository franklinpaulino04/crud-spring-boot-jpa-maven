package com.example.examplecrud.services;

import com.example.examplecrud.dtos.PostRequestDto;
import com.example.examplecrud.dtos.PostResponseDto;
import com.example.examplecrud.dtos.UserRequestDto;
import com.example.examplecrud.dtos.UserResponseDto;

import java.util.List;

public interface IPostService {
    List<PostResponseDto> findAllPosts();
    PostResponseDto getPost(Long id);
    PostResponseDto addPost(PostRequestDto postRequestDto);
    PostResponseDto updatePost(Long id, PostRequestDto postRequestDto);
    void deletePost(Long id);
}
