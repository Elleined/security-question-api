package com.elleined.security_question_api.user_defined_security_question;

import com.elleined.security_question_api.exception.SecurityQuestionAPIException;
import com.elleined.security_question_api.paging.PageRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public interface UserDefinedSecurityQuestionService {
    List<UserDefinedSecurityQuestionDTO> getAll(@org.hibernate.validator.constraints.UUID UUID resourceId,
                                                @NotNull PageRequest request);

    int getAllTotal(@org.hibernate.validator.constraints.UUID UUID resourceId);

    void save(@org.hibernate.validator.constraints.UUID UUID resourceId,
              @NotBlank String question,
              @NotBlank String answer) throws SecurityQuestionAPIException;

    void updateAnswer(@NotNull UserDefinedSecurityQuestionRequest request) throws SecurityQuestionAPIException;

    boolean isAnswerCorrect(@NotNull UserDefinedSecurityQuestionRequest request);
}
