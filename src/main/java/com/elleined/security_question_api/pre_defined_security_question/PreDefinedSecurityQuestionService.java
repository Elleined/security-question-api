package com.elleined.security_question_api.pre_defined_security_question;

import com.elleined.security_question_api.exception.SecurityQuestionAPIException;
import com.elleined.security_question_api.paging.PageRequest;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public interface PreDefinedSecurityQuestionService {

    List<PreDefinedSecurityQuestionDTO> getAll(@org.hibernate.validator.constraints.UUID UUID resourceId,
                                               @NotNull PageRequest request);

    int getAllTotal(@org.hibernate.validator.constraints.UUID UUID resourceId);

    void save(@NotNull PreDefinedSecurityQuestionRequest request) throws SecurityQuestionAPIException;

    void updateAnswer(@NotNull PreDefinedSecurityQuestionRequest request) throws SecurityQuestionAPIException;

    boolean isAnswerCorrect(@NotNull PreDefinedSecurityQuestionRequest request);
}
