package com.elleined.securityquestionapi.mapper;

import com.elleined.securityquestionapi.dto.PreDefinedQuestionDTO;
import com.elleined.securityquestionapi.model.PreDefinedQuestion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PreDefinedQuestionMapper extends CustomMapper<PreDefinedQuestion, PreDefinedQuestionDTO> {

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "createdAt", source = "createdAt"),

            @Mapping(target = "question", source = "question")
    })
    PreDefinedQuestionDTO toDTO(PreDefinedQuestion preDefinedQuestion);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())"),

            @Mapping(target = "question", source = "question")
    })
    PreDefinedQuestion toEntity(String question);
}
