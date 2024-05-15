package com.example.examplecrud.services;

import com.example.examplecrud.dtos.UserRequestDto;
import com.example.examplecrud.dtos.UserResponseDto;

import java.util.List;

public interface IUserService {
    List<UserResponseDto> findAllUsers();
    UserResponseDto getUser(Long id);
    UserResponseDto addUser(UserRequestDto userRequestDto);
    UserResponseDto updateUser(Long id, UserRequestDto userRequestDto);
    void deleteUser(Long id);
}
