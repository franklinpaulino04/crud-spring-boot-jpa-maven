package com.example.examplecrud.mappers;

import com.example.examplecrud.dtos.UserRequestDto;
import com.example.examplecrud.entities.User;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IUserRequestMapper {
    User toEntity(UserRequestDto userRequestDto);

    UserRequestDto toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserRequestDto userRequestDto, @MappingTarget User user);
}