package com.elleined.securityquestionapi.mapper;

import com.elleined.securityquestionapi.controller.UserController;
import com.elleined.securityquestionapi.dto.UserDTO;
import com.elleined.securityquestionapi.mapper.question.CustomQuestionMapper;
import com.elleined.securityquestionapi.mapper.question.PreDefinedQuestionMapper;
import com.elleined.securityquestionapi.model.User;
import org.mapstruct.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Mapper(
        componentModel = "spring",
        builder = @Builder(disableBuilder = true),
        uses = {
                SecurityQuestionMapper.class,
                CustomQuestionMapper.class
        }
)
public interface UserMapper {

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),

            @Mapping(target = "customQuestionDTOS", source = "customQuestions"),
            @Mapping(target = "securityQuestionDTOS", source = "securityQuestions")
    })
    UserDTO toDTO(User user);

    @Mappings({
            @Mapping(target = "id", ignore = true),

            @Mapping(target = "name", expression = "java(name)"),

            @Mapping(target = "securityQuestions", expression = "java(new java.util.ArrayList<>())"),
            @Mapping(target = "customQuestions", expression = "java(new java.util.ArrayList<>())")
    })
    User toEntity(String name);

    @AfterMapping
    default void toDTOAfterMapping(User user, @MappingTarget UserDTO userDTO) {
        userDTO.add(
                linkTo(methodOn(UserController.class).getById(user.getId()))
                        .withSelfRel()
                        .withType("GET")
                        .withTitle("Get by id")
                        .andAffordance(afford(methodOn(UserController.class).save(null)))
        );
    }
}
