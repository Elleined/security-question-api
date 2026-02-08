package com.elleined.security_question_api.pre_defined_security_question;

import com.elleined.security_question_api.exception.SecurityQuestionAPIException;
import com.elleined.security_question_api.paging.PageRequest;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public interface PreDefinedSecurityQuestionService {

    List<PreDefinedSecurityQuestionDTO> getAll(UUID resourceId,
                                               @NotNull PageRequest request);

    int getAllTotal(UUID resourceId);

    void save(@NotNull PreDefinedSecurityQuestionRequest request) throws SecurityQuestionAPIException;

    void updateAnswer(@NotNull PreDefinedSecurityQuestionRequest request) throws SecurityQuestionAPIException;

    boolean isAnswerCorrect(@NotNull PreDefinedSecurityQuestionRequest request);
}
