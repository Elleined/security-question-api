package com.elleined.securityquestionapi.mapper.question;

import com.elleined.securityquestionapi.dto.question.CustomQuestionDTO;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.sq.UserDefinedSecurityQuestion;
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
            @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())"),
            @Mapping(target = "answer", expression = "java(answer)")
    })
    UserDefinedSecurityQuestion toEntity(User currentUser,
                                         String question,
                                         @Context String answer);

    @Mappings({
            @Mapping(target = "ownerId", source = "owner.id")
    })
    CustomQuestionDTO toDTO(UserDefinedSecurityQuestion userDefinedQuestion);
}
