package com.example.examplecrud.services.impl;

import com.example.examplecrud.dtos.UserDto;
import com.example.examplecrud.dtos.UserRequestDto;
import com.example.examplecrud.dtos.UserResponseDto;
import com.example.examplecrud.entities.User;
import com.example.examplecrud.mappers.IUserMapper;
import com.example.examplecrud.mappers.IUserRequestMapper;
import com.example.examplecrud.mappers.IUserResponseMapper;
import com.example.examplecrud.repositories.UserRepository;
import com.example.examplecrud.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    IUserRequestMapper userRequestMapper;

    @Autowired
    IUserResponseMapper userResponseMapper;

    @Override
    public Page<User> findAllUsers(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return userRepository.findAll(pageable);
    }

    @Override
    public UserResponseDto getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id.toString()));
        return userResponseMapper.toDto(user);
    }

    @Override
    public UserResponseDto addUser(UserRequestDto addUserRequest) {
        User user = userRequestMapper.toEntity(addUserRequest);
        User savedUser = userRepository.save(user);
        return userResponseMapper.toDto(savedUser);
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto updateUserRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id.toString()));
        User updatedUser = userRequestMapper.partialUpdate(updateUserRequest, user);
        User savedUser = userRepository.save(updatedUser);
        return userResponseMapper.toDto(savedUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id.toString()));
        userRepository.delete(user);
    }
}
