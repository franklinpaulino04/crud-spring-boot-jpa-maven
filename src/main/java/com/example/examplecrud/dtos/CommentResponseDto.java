package com.example.examplecrud.dtos;

import com.example.examplecrud.entities.Post;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.examplecrud.entities.Comment}
 */
@Value
public class CommentResponseDto implements Serializable {
    Long id;
    Post post;
    String name;
    String email;
    String body;
}