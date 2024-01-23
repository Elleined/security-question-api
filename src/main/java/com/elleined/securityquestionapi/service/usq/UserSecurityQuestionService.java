package com.elleined.securityquestionapi.service.usq;

import com.elleined.securityquestionapi.exception.resource.ResourceNotFoundException;
import com.elleined.securityquestionapi.model.Question;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.UserSecurityQuestion;

import java.util.List;

public interface UserSecurityQuestionService {

    List<UserSecurityQuestion> getAllByUser(User currentUser);
    boolean isAnswerCorrect(User currentUser, UserSecurityQuestion userSecurityQuestion, String providedAnswer);
    UserSecurityQuestion save(User currentUser, Question question, String answer);

    UserSecurityQuestion getById(int id) throws ResourceNotFoundException;
}
