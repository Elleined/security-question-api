package com.elleined.securityquestionapi.service.user;

import com.elleined.securityquestionapi.model.Question;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.UserSecurityQuestion;

public interface UserService {
    User save(String name);
    User getById(int id);

    boolean isAnswerCorrect(User currentUser, Question question, String answer);
    UserSecurityQuestion save(User currentUser, Question question, String answer);
}
