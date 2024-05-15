package com.example.examplecrud.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "name")
    @NotBlank(message = "name is required")
    private String name;

    @Size(max = 255)
    @Column(name = "username")
    @NotBlank(message = "username is required")
    private String username;

    @Size(max = 255)
    @Column(name = "email")
    @Email(message = "email is not valid")
    @NotBlank(message = "email is required")
    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @JsonIgnore
    private Set<Post> posts = new LinkedHashSet<>();

}