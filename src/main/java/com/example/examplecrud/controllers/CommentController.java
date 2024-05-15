package com.example.examplecrud.controllers;

import com.example.examplecrud.dtos.*;
import com.example.examplecrud.services.ICommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Comments", description = "Comments API")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    @Autowired
    ICommentService commentService;

    @Operation(summary = "Get all comments", description = "Get all comments")
    @GetMapping
    public SendResponseDto getComments() {
        List<CommentResponseDto> comments = commentService.findAllComments();
        return new SendResponseDto(true,"",  comments);
    }

    @Operation(summary = "Get comment by id", description = "Get comment by id")
    @GetMapping("/{id}")
    public SendResponseDto getComment(@PathVariable Long id) {
        CommentResponseDto comment = commentService.getComment(id);
        return new SendResponseDto(true,"",  comment);
    }

    @Operation(summary = "Create comment", description = "Create comment")
    @PostMapping
    public SendResponseDto createComment(@Valid @RequestBody CommentRequestDto request) {
        CommentResponseDto comment = commentService.addComment(request);
        return new SendResponseDto(true,"Comment created successfully.",  comment);
    }

    @Operation(summary = "Update comment", description = "Update comment")
    @PutMapping("/{id}")
    public SendResponseDto updateComment(@PathVariable Long id, @Valid @RequestBody CommentRequestDto request) {
        CommentResponseDto updatedComment = commentService.updateComment(id, request);
        return new SendResponseDto(true,"Comment updated successfully.",  updatedComment);
    }

    @Operation(summary = "Delete comment", description = "Delete comment")
    @DeleteMapping("/{id}")
    public SendResponseDto deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return new SendResponseDto(true,"Comment deleted successfully.",  new Object());
    }
}
