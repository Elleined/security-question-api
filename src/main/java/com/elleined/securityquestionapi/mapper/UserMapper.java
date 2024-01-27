package com.elleined.securityquestionapi.mapper;

import com.elleined.securityquestionapi.dto.UserDTO;
import com.elleined.securityquestionapi.model.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);

    @Mappings({
            @Mapping(target = "id", ignore = true),

            @Mapping(target = "name", expression = "java(name)"),

            @Mapping(target = "securityQuestions", expression = "java(new java.util.ArrayList<>())"),
            @Mapping(target = "customQuestions", expression = "java(new java.util.ArrayList<>())")
    })
    User toEntity(String name);

    @BeforeMapping
    default void toDTOBeforeMapping(User user, @MappingTarget UserDTO.UserDTOBuilder userDTOBuilder) {
        System.out.println("HI");
        // Automatically execute code before toDTO method
    }

    @AfterMapping
    default void toDTOAfterMapping(User user, @MappingTarget UserDTO.UserDTOBuilder userDTOBuilder) {
        // Automatically execute code after toDTO method
    }
}
