package com.elleined.securityquestionapi.mapper;

import com.elleined.securityquestionapi.dto.UserDTO;
import com.elleined.securityquestionapi.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);

    @Mappings({
            @Mapping(target = "id", ignore = true),

            @Mapping(target = "name", expression = "java(name)"),

            @Mapping(target = "securityQuestions", expression = "java(new java.util.ArrayList<>())")
    })
    User toEntity(String name);
}
