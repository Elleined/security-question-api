package com.elleined.securityquestionapi.service.question.pdq;

import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.question.PreDefinedQuestion;
import com.elleined.securityquestionapi.service.question.QuestionService;

import java.util.List;

public interface PreDefinedQuestionService extends QuestionService<PreDefinedQuestion> {
    PreDefinedQuestion save(String question);
    List<PreDefinedQuestion> saveAll(List<String> questions);
}
