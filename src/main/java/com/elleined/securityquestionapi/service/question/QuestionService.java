package com.elleined.securityquestionapi.service.question;

import com.elleined.securityquestionapi.model.Question;
import com.elleined.securityquestionapi.model.User;

import java.util.List;

public interface QuestionService {
    Question save(String question);
    Question getById(int id);
    List<Question> getAll();
}
