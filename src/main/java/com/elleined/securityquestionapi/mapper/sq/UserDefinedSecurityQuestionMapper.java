package com.elleined.securityquestionapi.mapper.sq;

import com.elleined.securityquestionapi.dto.sq.UserDefinedSecurityQuestionDTO;
import com.elleined.securityquestionapi.mapper.CustomMapper;
import com.elleined.securityquestionapi.mapper.UserMapper;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.sq.UserDefinedSecurityQuestion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(
        componentModel = "spring",
        uses = UserMapper.class
)
public interface UserDefinedSecurityQuestionMapper extends CustomMapper<UserDefinedSecurityQuestion, UserDefinedSecurityQuestionDTO> {

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "createdAt", source = "createdAt"),

            @Mapping(target = "ownerDTO", source = "owner"),

            @Mapping(target = "question", source = "question")
    })
    UserDefinedSecurityQuestionDTO toDTO(UserDefinedSecurityQuestion userDefinedSecurityQuestion);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())"),

            @Mapping(target = "owner", source = "owner"),
            @Mapping(target = "answer", source = "answer"),

            @Mapping(target = "question", source = "question")
    })
    UserDefinedSecurityQuestion toEntity(User owner,
                                         String question,
                                         String answer);
}
