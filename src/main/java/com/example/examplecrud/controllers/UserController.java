package com.example.examplecrud.controllers;

import com.example.examplecrud.dtos.SendResponseDto;
import com.example.examplecrud.dtos.UserRequestDto;
import com.example.examplecrud.dtos.UserResponseDto;
import com.example.examplecrud.services.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users", description = "Users API")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    IUserService userService;

    @Operation(summary = "Get all users", description = "Get all users")
    @GetMapping
    public SendResponseDto getUsers() {
        List<UserResponseDto> users = userService.findAllUsers();
        return new SendResponseDto(true,"",  users);
    }

    @Operation(summary = "Get user by id", description = "Get user by id")
    @GetMapping("/{id}")
    public SendResponseDto getUser(@PathVariable Long id) {
        UserResponseDto user = userService.getUser(id);
        return new SendResponseDto(true,"",  user);
    }

    @Operation(summary = "Create user", description = "Create user")
    @PostMapping
    public SendResponseDto createUser(@Valid @RequestBody UserRequestDto request) {
        UserResponseDto user = userService.addUser(request);
        return new SendResponseDto(true,"User created successfully.",  user);
    }

    @Operation(summary = "Update user", description = "Update user")
    @PutMapping("/{id}")
    public SendResponseDto updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDto request) {
        UserResponseDto updatedUser = userService.updateUser(id, request);
        return new SendResponseDto(true,"User updated successfully.",  updatedUser);
    }

    @Operation(summary = "Delete user", description = "Delete user")
    @DeleteMapping("/{id}")
    public SendResponseDto deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new SendResponseDto(true,"User deleted successfully.",  new Object());
    }
}
