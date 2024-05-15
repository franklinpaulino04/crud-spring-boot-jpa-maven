package com.example.examplecrud.dtos;

import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.example.examplecrud.entities.User}
 */
@Value
public class UserDto implements Serializable {
    Long id;
    @Size(max = 255)
    String name;
    @Size(max = 255)
    String username;
    @Size(max = 255)
    String email;
    Set<PostDto> posts;
}