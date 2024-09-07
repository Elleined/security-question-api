package com.elleined.securityquestionapi.service.user;

import com.elleined.securityquestionapi.exception.resource.ResourceNotFoundException;
import com.elleined.securityquestionapi.model.User;

public interface UserService {
    User getById(int id);

    User getByJWT(String jwt) throws ResourceNotFoundException;

    User register(String name,
                  String email,
                  String password);

    String login(String email,
                 String password);
}
