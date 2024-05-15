package com.example.examplecrud.dtos;

import com.example.examplecrud.entities.Comment;
import com.example.examplecrud.entities.User;
import lombok.Value;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * DTO for {@link com.example.examplecrud.entities.Post}
 */
@Value
public class PostResponseDto implements Serializable {
    Long id;
    String title;
    String body;
    User user;
    Set<Comment> comments = new LinkedHashSet<>();
}