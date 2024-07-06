package com.elleined.securityquestionapi.service.question.pdq;

import com.elleined.securityquestionapi.model.PreDefinedQuestion;
import com.elleined.securityquestionapi.service.question.QuestionService;

import java.util.List;

public interface PreDefinedQuestionService extends QuestionService<PreDefinedQuestion> {
    PreDefinedQuestion save(String question);
    List<PreDefinedQuestion> saveAll(List<String> questions);

    List<PreDefinedQuestion> getAll();
}
