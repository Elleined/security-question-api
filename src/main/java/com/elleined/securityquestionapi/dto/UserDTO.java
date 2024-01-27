package com.elleined.securityquestionapi.dto;

import com.elleined.securityquestionapi.dto.question.CustomQuestionDTO;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Getter
public class UserDTO extends RepresentationModel<UserDTO> {
    private final int id;
    private final String name;

    private final List<SecurityQuestionDTO> securityQuestionDTOS;
    private final List<CustomQuestionDTO> customQuestionDTOS;

    public UserDTO(int id, String name, List<SecurityQuestionDTO> securityQuestionDTOS, List<CustomQuestionDTO> customQuestionDTOS) {
        this.id = id;
        this.name = name;
        this.securityQuestionDTOS = securityQuestionDTOS;
        this.customQuestionDTOS = customQuestionDTOS;
    }
}
