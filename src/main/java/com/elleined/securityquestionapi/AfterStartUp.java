package com.elleined.securityquestionapi;

import com.elleined.securityquestionapi.populator.PreDefinedQuestionPopulator;
import com.elleined.securityquestionapi.service.PreDefinedQuestionService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class AfterStartUp {

    private final PreDefinedQuestionPopulator preDefinedQuestionPopulator;
    private final PreDefinedQuestionService preDefinedQuestionService;

    @PostConstruct
    void init() {
        if (preDefinedQuestionService.getById(1) != null) {
            System.out.println("Returning because there's already saved questions");
            return;
        }

        System.out.println("Saving questions! Please wait...");
        preDefinedQuestionPopulator.populate();
        System.out.println("Saving questions success...");
    }
}
