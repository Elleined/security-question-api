package com.elleined.securityquestionapi.service.question.cq;

import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.question.CustomQuestion;
import com.elleined.securityquestionapi.service.question.QuestionService;

import java.util.List;

public interface CustomQuestionService extends QuestionService<CustomQuestion> {
    CustomQuestion save(User currentUser, String question, String answer);
    boolean isAnswerCorrect(User currentUser, CustomQuestion customQuestion, String providedAnswer);
    List<CustomQuestion> getAllByOwner(User currentUser);
}
