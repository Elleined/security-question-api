package com.elleined.securityquestionapi.service.question.cq;

import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.question.CustomQuestion;
import com.elleined.securityquestionapi.model.question.Question;
import com.elleined.securityquestionapi.service.question.QuestionService;

import java.util.List;

public interface CustomQuestionService extends QuestionService<CustomQuestion> {
    CustomQuestion save(User currentUser, String question);
    List<CustomQuestion> saveAll(User currentUser, List<String> questions);
}
