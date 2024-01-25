package com.elleined.securityquestionapi.service.question;

import com.elleined.securityquestionapi.model.question.Question;

public interface QuestionService<T extends Question> {
    T getById(int id);
    boolean existsById(int id);
    boolean alreadyExists(String question);
}
