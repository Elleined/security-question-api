package com.elleined.securityquestionapi.service.question.pdq;

import com.elleined.securityquestionapi.model.question.PreDefinedQuestion;
import com.elleined.securityquestionapi.service.question.QuestionService;

public interface PreDefinedQuestionService extends QuestionService<PreDefinedQuestion> {
    PreDefinedQuestion save(String question);
}
