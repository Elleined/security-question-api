package com.elleined.securityquestionapi.controller;

import com.elleined.securityquestionapi.model.Question;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.UserSecurityQuestion;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{currentUserId}/security-questions")
public class UserSecurityQuestionController {

    public boolean isAnswerCorrect(User currentUser, Question question, String answer) {
        return false;
    }

    public UserSecurityQuestion save(User currentUser, Question question, String answer) {
        return null;
    }
}
