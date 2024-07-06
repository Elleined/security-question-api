package com.elleined.securityquestionapi.mapper;


import com.elleined.securityquestionapi.model.PrimaryKeyIdentity;

public interface CustomMapper<ENTITY extends PrimaryKeyIdentity,
        DTO extends com.elleined.securityquestionapi.dto.DTO> {
    DTO toDTO(ENTITY entity);
}