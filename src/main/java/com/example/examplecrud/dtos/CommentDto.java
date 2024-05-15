package com.example.examplecrud.dtos;

import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.examplecrud.entities.Comment}
 */
@Value
public class CommentDto implements Serializable {
    Long id;
    PostDto post;
    @Size(max = 255)
    String name;
    @Size(max = 255)
    String email;
    @Size(max = 255)
    String body;
}