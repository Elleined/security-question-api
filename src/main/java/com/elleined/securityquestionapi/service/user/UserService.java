package com.elleined.securityquestionapi.service.user;

import com.elleined.securityquestionapi.model.User;

public interface UserService extends UserSecurityQuestionService {
    User save(String name);
    User getById(int id);
}
