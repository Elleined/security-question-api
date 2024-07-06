package com.elleined.securityquestionapi.service.sq;

import com.elleined.securityquestionapi.exception.resource.ResourceNotFoundException;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.PreDefinedQuestion;

import java.util.List;

public interface SecurityQuestionService {
    int SECURITY_QUESTION_LIMIT = 3;

    List<SecurityQuestion> getAllByUser(User currentUser);
    boolean isAnswerCorrect(User currentUser, SecurityQuestion securityQuestion, String providedAnswer);
    SecurityQuestion save(User currentUser, PreDefinedQuestion preDefinedQuestion, String answer);

    SecurityQuestion getById(int id) throws ResourceNotFoundException;

    default boolean hasReachedLimitOfSecurityQuestions(User currentUser) {
        return currentUser.getSecurityQuestions().size() == SecurityQuestionService.SECURITY_QUESTION_LIMIT;
    }
}
