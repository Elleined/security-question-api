package com.elleined.securityquestionapi.mapper;

import com.elleined.securityquestionapi.model.Question;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.UserSecurityQuestion;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserSecurityQuestionMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),

            @Mapping(target = "answer", expression = "java(answer)"),
            @Mapping(target = "question", expression = "java(question)"),
            @Mapping(target = "user", expression = "java(currentUser)"),

            @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())"),
    })
    UserSecurityQuestion toEntity(User currentUser,
                                  @Context Question question,
                                  @Context String answer);
}
