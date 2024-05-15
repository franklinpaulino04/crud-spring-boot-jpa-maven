package com.example.examplecrud.controllers;

import com.example.examplecrud.dtos.*;
import com.example.examplecrud.entities.Post;
import com.example.examplecrud.entities.User;
import com.example.examplecrud.services.IPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Posts", description = "Posts API")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    IPostService postService;

    @Operation(summary = "Get all posts paginate", description = "Get all posts paginate")
    @GetMapping
    public Page<PostDto> getPosts(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDirection) {
        return postService.findAllPosts(pageNo, pageSize, sortBy, sortDirection);
    }

    @Operation(summary = "Get post by id", description = "Get post by id")
    @GetMapping("/{id}")
    public SendResponseDto getPost(@PathVariable Long id) {
        PostResponseDto post = postService.getPost(id);
        return new SendResponseDto(true,"",  post);
    }

    @Operation(summary = "Create post", description = "Create post")
    @PostMapping
    public SendResponseDto createPost(@Valid @RequestBody PostRequestDto request) {
        PostResponseDto post = postService.addPost(request);
        return new SendResponseDto(true,"Post created successfully.",  post);
    }

    @Operation(summary = "Update post", description = "Update post")
    @PutMapping("/{id}")
    public SendResponseDto updatePost(@PathVariable Long id, @Valid @RequestBody PostRequestDto request) {
        PostResponseDto updatedPost = postService.updatePost(id, request);
        return new SendResponseDto(true,"Post updated successfully.",  updatedPost);
    }

    @Operation(summary = "Delete post", description = "Delete post")
    @DeleteMapping("/{id}")
    public SendResponseDto deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return new SendResponseDto(true,"Post deleted successfully.",  new Object());
    }
}
