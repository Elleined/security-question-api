package com.elleined.securityquestionapi.mapper.question;

import com.elleined.securityquestionapi.dto.question.CustomQuestionDTO;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.question.CustomQuestion;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
@Mapper(componentModel = "spring")
public interface CustomQuestionMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),

            @Mapping(target = "question", expression = "java(question)"),
            @Mapping(target = "owner", expression = "java(currentUser)"),

            @Mapping(target = "securityQuestions", expression = "java(new java.util.ArrayList<>())")
    })
    CustomQuestion toEntity(User currentUser,
                            @Context String question);

    @Mappings({
            @Mapping(target = "ownerId", source = "owner.id")
    })
    CustomQuestionDTO toDTO(CustomQuestion customQuestion);
}