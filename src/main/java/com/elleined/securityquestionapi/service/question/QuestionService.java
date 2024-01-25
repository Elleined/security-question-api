package com.elleined.securityquestionapi.service.question;

import com.elleined.securityquestionapi.model.question.Question;

import java.util.List;

public interface QuestionService<T extends Question> {
    T getById(int id);
    boolean existsById(int id);
    boolean alreadyExists(String question);
    List<T> getAll();
}
