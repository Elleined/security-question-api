package com.elleined.securityquestionapi.service.sq;

import com.elleined.securityquestionapi.exception.resource.ResourceNotFoundException;
import com.elleined.securityquestionapi.model.Question;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.SecurityQuestion;

import java.util.List;

public interface SecurityQuestionService {

    List<SecurityQuestion> getAllByUser(User currentUser);
    boolean isAnswerCorrect(User currentUser, SecurityQuestion securityQuestion, String providedAnswer);
    SecurityQuestion save(User currentUser, Question question, String answer);

    SecurityQuestion getById(int id) throws ResourceNotFoundException;
}
