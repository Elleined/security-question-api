package com.elleined.securityquestionapi.service.sq;

import com.elleined.securityquestionapi.exception.resource.ResourceNotFoundException;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.sq.SecurityQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SecurityQuestionService<T extends SecurityQuestion> {
    int SECURITY_QUESTION_LIMIT = 3;

    T getById(int id) throws ResourceNotFoundException;
    Page<T> getAll(User currentUser, Pageable pageable);

    boolean isAnswerCorrect(User currentUser, T securityQuestion, String providedAnswer);

    default boolean isLimitReached(User currentUser) {
        return currentUser.getPreDefinedSecurityQuestions().size() +
                currentUser.getUserDefinedQuestions().size() >= SECURITY_QUESTION_LIMIT;
    }
}
