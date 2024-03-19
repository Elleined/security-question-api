package com.elleined.securityquestionapi.dto;

import com.elleined.securityquestionapi.dto.question.CustomQuestionDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class UserDTO {
    private final int id;
    private final String name;

    private final List<Integer> securityQuestionIds;
    private final List<Integer> customQuestionIds;

    @Builder
    public UserDTO(int id, String name, List<Integer> securityQuestionIds, List<Integer> customQuestionIds) {
        this.id = id;
        this.name = name;
        this.securityQuestionIds = securityQuestionIds;
        this.customQuestionIds = customQuestionIds;
    }
}
