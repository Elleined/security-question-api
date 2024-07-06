package com.elleined.securityquestionapi.service.sq.udsq;

import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.sq.UserDefinedSecurityQuestion;
import com.elleined.securityquestionapi.service.sq.SecurityQuestionService;

public interface UserDefinedSecurityQuestionService extends SecurityQuestionService<UserDefinedSecurityQuestion> {
    UserDefinedSecurityQuestion save(User currentUser, String question, String answer);
}
