package com.elleined.securityquestionapi.mapper;

import com.elleined.securityquestionapi.dto.UserDTO;
import com.elleined.securityquestionapi.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "customQuestionIds", expression = "java(user.securityQuestionIds())"),
            @Mapping(target = "securityQuestionIds", expression = "java(user.customQuestionIds())")
    })
    UserDTO toDTO(User user);

    @Mappings({
            @Mapping(target = "id", ignore = true),

            @Mapping(target = "name", expression = "java(name)"),

            @Mapping(target = "securityQuestions", expression = "java(new java.util.ArrayList<>())"),
            @Mapping(target = "customQuestions", expression = "java(new java.util.ArrayList<>())")
    })
    User toEntity(String name);
}
