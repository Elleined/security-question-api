package com.elleined.securityquestionapi.service.question;

import com.elleined.securityquestionapi.model.Question;

import java.util.List;

public interface QuestionService {
    Question save(String question);

    List<Question> saveAll(List<String> questions);

    Question getById(int id);
    boolean existsById(int id);
    List<Question> getAll();
}
