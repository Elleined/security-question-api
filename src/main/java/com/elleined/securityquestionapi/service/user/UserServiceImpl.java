package com.elleined.securityquestionapi.service.user;

import com.elleined.securityquestionapi.model.Question;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.UserSecurityQuestion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Override
    public boolean isAnswerCorrect(User currentUser, Question question, String answer) {
        return false;
    }

    @Override
    public UserSecurityQuestion save(User currentUser, Question question, String answer) {
        return null;
    }

    @Override
    public User save(String name) {
        return null;
    }

    @Override
    public User getById(int id) {
        return null;
    }
}
