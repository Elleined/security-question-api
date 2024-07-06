package com.elleined.securityquestionapi.service.sq.pdsq;

import com.elleined.securityquestionapi.model.PreDefinedQuestion;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.sq.PreDefinedSecurityQuestion;
import com.elleined.securityquestionapi.service.sq.SecurityQuestionService;

public interface PreDefinedSecurityQuestionService extends SecurityQuestionService<PreDefinedSecurityQuestion> {
    PreDefinedSecurityQuestion save(User currentUser, PreDefinedQuestion preDefinedQuestion, String answer);
}
