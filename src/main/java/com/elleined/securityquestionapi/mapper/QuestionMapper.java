package com.elleined.securityquestionapi.mapper;

import com.elleined.securityquestionapi.model.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),

            @Mapping(target = "question", expression = "java(question)"),
            @Mapping(target = "userSecurityQuestions", expression = "java(new java.util.ArrayList<>())"),
    })
    Question toEntity(String question);
}
