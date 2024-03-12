package com.elleined.securityquestionapi.mapper;

import com.elleined.securityquestionapi.dto.SecurityQuestionDTO;
import com.elleined.securityquestionapi.model.SecurityQuestion;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.question.PreDefinedQuestion;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SecurityQuestionMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())"),
            @Mapping(target = "answer", expression = "java(answer)"),
            @Mapping(target = "owner", expression = "java(currentUser)"),
            @Mapping(target = "preDefinedQuestion", expression = "java(preDefinedQuestion)")
    })
    SecurityQuestion toEntity(User currentUser,
                              @Context PreDefinedQuestion preDefinedQuestion,
                              @Context String answer);

    @Mappings({
            @Mapping(target = "owner", source = "owner"),
            @Mapping(target = "preDefinedQuestionDTO", source = "preDefinedQuestion")
    })
    SecurityQuestionDTO toDTO(SecurityQuestion securityQuestion);
}
