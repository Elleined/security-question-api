package com.elleined.securityquestionapi.service.user;

import com.elleined.securityquestionapi.model.User;

public interface UserService {
    User save(String name);
    User getById(int id);
}
