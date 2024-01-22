package com.elleined.securityquestionapi.populator;

import com.elleined.securityquestionapi.model.Question;
import com.elleined.securityquestionapi.service.question.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class QuestionPopulator implements Populator {
    private final QuestionService questionService;

    @Override
    public void populate() {
        Question question1 = Question.builder()
                .question("What was the house number and street you lived in as a child?")
                .userSecurityQuestions(new ArrayList<>())
                .build();



    }
}
