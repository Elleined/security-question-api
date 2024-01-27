package com.elleined.securityquestionapi.mapper;

import com.elleined.securityquestionapi.controller.SecurityQuestionController;
import com.elleined.securityquestionapi.dto.SecurityQuestionDTO;
import com.elleined.securityquestionapi.model.SecurityQuestion;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.question.Question;
import org.mapstruct.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface SecurityQuestionMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),

            @Mapping(target = "answer", expression = "java(answer)"),
            @Mapping(target = "question", expression = "java(question)"),
            @Mapping(target = "user", expression = "java(currentUser)"),

            @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())"),
    })
    SecurityQuestion toEntity(User currentUser,
                              @Context Question question,
                              @Context String answer);

    @Mappings({

            @Mapping(target = "owner", source = "owner"),
            @Mapping(target = "preDefinedQuestionDTO", source = "preDefinedQuestion")
    })
    SecurityQuestionDTO toDTO(SecurityQuestion securityQuestion);

    @AfterMapping
    default void toDTOAfterMapping(SecurityQuestion securityQuestion, @MappingTarget SecurityQuestionDTO securityQuestionDTO) {
        securityQuestionDTO.add(
                linkTo(methodOn(SecurityQuestionController.class).getAllByUser(securityQuestion.getOwner().getId()))
                        .withSelfRel()
                        .withTitle("Get all current user security questions")
                        .withType("GET"),
                linkTo(methodOn(SecurityQuestionController.class).save(null, securityQuestion.getId(), null))
                        .withSelfRel()
                        .withType("POST")
                        .withTitle("Save security question"),
                linkTo(methodOn(SecurityQuestionController.class).isAnswerCorrect(null, securityQuestion.getId(), null))
                        .withSelfRel()
                        .withTitle("Check if security question answer is correct")
                        .withType("GET")
        );
    }

    default List<SecurityQuestionDTO> toUserSecurityQuestion(List<SecurityQuestion> securityQuestions) {
        return securityQuestions.stream()
                .map(this::toDTO)
                .toList();
    }
}
