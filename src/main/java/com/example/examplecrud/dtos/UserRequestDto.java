package com.example.examplecrud.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.example.examplecrud.entities.User}
 */
@Value
public class UserRequestDto implements Serializable {
    @NotBlank(message = "name is required")
    String name;

    @NotBlank(message = "username is required")
    String username;

    @Email(message = "email is not valid")
    @NotBlank(message = "email is required")
    String email;
}