package com.elleined.securityquestionapi.mapper.sq;

import com.elleined.securityquestionapi.dto.sq.PreDefinedSecurityQuestionDTO;
import com.elleined.securityquestionapi.mapper.CustomMapper;
import com.elleined.securityquestionapi.mapper.PreDefinedQuestionMapper;
import com.elleined.securityquestionapi.mapper.UserMapper;
import com.elleined.securityquestionapi.model.PreDefinedQuestion;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.sq.PreDefinedSecurityQuestion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(
        componentModel = "spring",
        uses = {
                UserMapper.class,
                PreDefinedQuestionMapper.class
        }
)
public interface PreDefinedSecurityQuestionMapper extends CustomMapper<PreDefinedSecurityQuestion, PreDefinedSecurityQuestionDTO> {

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "createdAt", source = "createdAt"),

            @Mapping(target = "ownerDTO", source = "owner"),

            @Mapping(target = "preDefinedQuestionDTO", source = "preDefinedQuestion"),
    })
    PreDefinedSecurityQuestionDTO toDTO(PreDefinedSecurityQuestion preDefinedSecurityQuestion);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())"),

            @Mapping(target = "owner", source = "owner"),
            @Mapping(target = "answer", source = "answer"),

            @Mapping(target = "preDefinedQuestion", source = "preDefinedQuestion")
    })
    PreDefinedSecurityQuestion toEntity(User owner,
                                        PreDefinedQuestion preDefinedQuestion,
                                        String answer);
}
