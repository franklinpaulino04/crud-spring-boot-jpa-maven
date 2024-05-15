package com.example.examplecrud.dtos;

import com.example.examplecrud.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.examplecrud.entities.Post}
 */
@Value
public class PostRequestDto implements Serializable {

    @NotBlank(message = "Title is required")
    String title;

    @NotBlank(message = "Body is required")
    String body;

    @NotNull(message = "User is required")
    User user;
}
