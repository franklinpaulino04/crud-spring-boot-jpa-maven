package com.example.examplecrud.mappers;

import com.example.examplecrud.dtos.UserResponseDto;
import com.example.examplecrud.entities.User;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IUserResponseMapper {
    User toEntity(UserResponseDto userResponseDto);

    @AfterMapping
    default void linkPosts(@MappingTarget User user) {
        user.getPosts().forEach(post -> post.setUser(user));
    }

    UserResponseDto toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserResponseDto userResponseDto, @MappingTarget User user);
}