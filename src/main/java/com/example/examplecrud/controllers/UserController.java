package com.example.examplecrud.controllers;

import com.example.examplecrud.dtos.SendResponseDto;
import com.example.examplecrud.dtos.UserDto;
import com.example.examplecrud.dtos.UserRequestDto;
import com.example.examplecrud.dtos.UserResponseDto;
import com.example.examplecrud.entities.User;
import com.example.examplecrud.services.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@Tag(name = "Users", description = "Users API")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    IUserService userService;

    @Operation(summary = "Get all users paginate", description = "Get all users paginate")
    @GetMapping
    public Page<User> findAllPaginateUsers(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDirection) {
        return userService.findAllUsers(pageNo, pageSize, sortBy, sortDirection);
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
