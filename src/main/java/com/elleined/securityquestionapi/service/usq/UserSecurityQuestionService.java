package com.elleined.securityquestionapi.service.usq;

import com.elleined.securityquestionapi.model.Question;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.UserSecurityQuestion;

public interface UserSecurityQuestionService {
    boolean isAnswerCorrect(User currentUser, UserSecurityQuestion userSecurityQuestion, String providedAnswer);
    UserSecurityQuestion save(User currentUser, Question question, String answer);
}
