package com.elleined.securityquestionapi;

import com.elleined.securityquestionapi.populator.QuestionPopulator;
import com.elleined.securityquestionapi.service.question.QuestionService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class AfterStartUp {
    private final QuestionPopulator questionPopulator;
    private final QuestionService questionService;

    @PostConstruct
    void init() {
        if (questionService.existsById(1)) {
            System.out.println("Returning because there's already saved questions");
            return;
        }

        System.out.println("Saving questions! Please wait...");
        questionPopulator.populate();
        System.out.println("Saving questions success...");
    }
}
