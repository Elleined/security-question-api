package com.elleined.securityquestionapi.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class UserDTO extends RepresentationModel<UserDTO> {
    private final int id;
    private final String name;

    @Builder
    public UserDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
