package com.elleined.securityquestionapi.mapper;

import com.elleined.securityquestionapi.dto.SecurityQuestionDTO;
import com.elleined.securityquestionapi.model.SecurityQuestion;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.question.Question;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

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

            @Mapping(target = "ownerId", source = "user.id"),
            @Mapping(target = "questionId", source = "question.id")
    })
    SecurityQuestionDTO toDTO(SecurityQuestion securityQuestion);
}
