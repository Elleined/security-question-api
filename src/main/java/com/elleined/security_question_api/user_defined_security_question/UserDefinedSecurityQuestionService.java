package com.elleined.security_question_api.user_defined_security_question;

import com.elleined.security_question_api.exception.SecurityQuestionAPIException;
import com.elleined.security_question_api.paging.PageRequest;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public interface UserDefinedSecurityQuestionService {
    List<UserDefinedSecurityQuestionDTO> getAll(UUID resourceId,
                                                @NotNull PageRequest request);

    int getAllTotal(UUID resourceId);

    void save(@NotNull UserDefinedSecurityQuestionSaveRequest request) throws SecurityQuestionAPIException;

    void updateAnswer(@NotNull UserDefinedSecurityQuestionRequest request) throws SecurityQuestionAPIException;

    boolean isAnswerCorrect(@NotNull UserDefinedSecurityQuestionRequest request);
}
