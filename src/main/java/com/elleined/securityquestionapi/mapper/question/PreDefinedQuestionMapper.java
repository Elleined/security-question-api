package com.elleined.securityquestionapi.mapper.question;

import com.elleined.securityquestionapi.dto.question.PreDefinedQuestionDTO;
import com.elleined.securityquestionapi.model.question.PreDefinedQuestion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PreDefinedQuestionMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "question", expression = "java(question)"),
            @Mapping(target = "securityQuestions", expression = "java(new java.util.ArrayList<>())")
    })
    PreDefinedQuestion toEntity(String question);

    PreDefinedQuestionDTO toDTO(PreDefinedQuestion definedQuestion);
}
