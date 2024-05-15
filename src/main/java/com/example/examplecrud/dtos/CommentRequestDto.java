package com.example.examplecrud.dtos;

import com.example.examplecrud.entities.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.examplecrud.entities.Comment}
 */
@Value
public class CommentRequestDto implements Serializable {
    @NotNull(message = "post is required")
    Post post;

    @NotBlank(message = "name is required")
    String name;

    @NotBlank(message = "email is required")
    String email;

    @NotBlank(message = "body is required")
    String body;
}
