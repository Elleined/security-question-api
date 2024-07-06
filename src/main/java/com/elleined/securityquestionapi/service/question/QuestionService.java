package com.elleined.securityquestionapi.service.question;

import com.elleined.securityquestionapi.model.question.SecurityQuestion;

public interface QuestionService<T extends SecurityQuestion> {
    T getById(int id);
    boolean existsById(int id);
    boolean alreadyExists(String question);
}
