package com.example.examplecrud.dtos;

import com.example.examplecrud.entities.User;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.example.examplecrud.entities.Post}
 */
@Value
public class PostDto implements Serializable {
    Long id;
    @Size(max = 255)
    String title;
    @Size(max = 255)
    String body;
    User user;
    Set<CommentDto> comments;
}