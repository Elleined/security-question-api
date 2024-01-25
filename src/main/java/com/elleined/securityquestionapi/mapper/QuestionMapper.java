package com.elleined.securityquestionapi.mapper;

import com.elleined.securityquestionapi.dto.QuestionDTO;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.question.CustomQuestion;
import com.elleined.securityquestionapi.model.question.PreDefinedQuestion;
import com.elleined.securityquestionapi.model.question.Question;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),

            @Mapping(target = "question", expression = "java(question)"),
            @Mapping(target = "owner", expression = "java(currentUser)"),

            @Mapping(target = "securityQuestions", expression = "java(new java.util.ArrayList<>())")
    })
    CustomQuestion toEntity(User currentUser,
                            @Context String question);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "question", expression = "java(question)"),
            @Mapping(target = "securityQuestions", expression = "java(new java.util.ArrayList<>())")
    })
    PreDefinedQuestion toEntity(String question);

    QuestionDTO toDTO(Question question);
}
