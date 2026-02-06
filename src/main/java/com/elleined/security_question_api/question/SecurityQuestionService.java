package com.elleined.security_question_api.question;

import com.elleined.security_question_api.paging.PageRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public interface SecurityQuestionService {

    List<SecurityQuestionDTO> getAll(String name,
                                     @NotNull PageRequest request);

    int getAllTotal(String name);

    void save(@NotBlank String name);

    void update(@org.hibernate.validator.constraints.UUID UUID id, @NotBlank String name);

    void delete(@org.hibernate.validator.constraints.UUID UUID id);
}
