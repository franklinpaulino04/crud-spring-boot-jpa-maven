package com.example.examplecrud.services;

import com.example.examplecrud.dtos.UserDto;
import com.example.examplecrud.dtos.UserRequestDto;
import com.example.examplecrud.dtos.UserResponseDto;
import com.example.examplecrud.entities.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IUserService {
    Page<UserDto> findAllUsers(int pageNo, int pageSize, String sortBy, String sortDirection);
    UserResponseDto getUser(Long id);
    UserResponseDto addUser(UserRequestDto userRequestDto);
    UserResponseDto updateUser(Long id, UserRequestDto userRequestDto);
    void deleteUser(Long id);
}
