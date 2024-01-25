package com.elleined.securityquestionapi;

import com.elleined.securityquestionapi.populator.PreDefinedQuestionPopulator;
import com.elleined.securityquestionapi.service.question.QuestionService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class AfterStartUp {
    private final PreDefinedQuestionPopulator preDefinedQuestionPopulator;
    private final QuestionService questionService;

    @PostConstruct
    void init() {
        if (questionService.existsById(1)) {
            System.out.println("Returning because there's already saved questions");
            return;
        }

        System.out.println("Saving questions! Please wait...");
        preDefinedQuestionPopulator.populate();
        System.out.println("Saving questions success...");
    }
}
