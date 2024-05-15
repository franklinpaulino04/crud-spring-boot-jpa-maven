package com.example.examplecrud.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ColumnDefault("0")
    @JoinColumn(name = "post_id")
    @NotNull(message = "post is required")
    private Post post;

    @Size(max = 255)
    @Column(name = "name")
    @NotBlank(message = "name is required")
    private String name;

    @Size(max = 255)
    @Column(name = "email")
    @Email(message = "email is not valid")
    @NotBlank(message = "email is not valid")
    private String email;

    @Size(max = 255)
    @Column(name = "body")
    @NotBlank(message = "body is required")
    private String body;

}