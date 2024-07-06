package com.elleined.securityquestionapi.service.question.cq;

import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.question.UserDefinedSecurityQuestion;
import com.elleined.securityquestionapi.service.question.QuestionService;

import java.util.List;

public interface CustomQuestionService extends QuestionService<UserDefinedSecurityQuestion> {
    UserDefinedSecurityQuestion save(User currentUser, String question, String answer);
    boolean isAnswerCorrect(User currentUser, UserDefinedSecurityQuestion userDefinedQuestion, String providedAnswer);
    List<UserDefinedSecurityQuestion> getAllByOwner(User currentUser);
}
