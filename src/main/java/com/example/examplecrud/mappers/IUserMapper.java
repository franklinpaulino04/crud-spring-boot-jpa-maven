package com.example.examplecrud.mappers;

import com.example.examplecrud.dtos.UserDto;
import com.example.examplecrud.entities.User;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {IPostMapper.class})
public interface IUserMapper {
    User toEntity(UserDto userDto);

    @AfterMapping
    default void linkPosts(@MappingTarget User user) {
        user.getPosts().forEach(post -> post.setUser(user));
    }

    UserDto toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserDto userDto, @MappingTarget User user);
}