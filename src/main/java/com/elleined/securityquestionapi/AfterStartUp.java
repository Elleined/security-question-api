package com.elleined.securityquestionapi;

import com.elleined.securityquestionapi.populator.PreDefinedQuestionPopulator;
import com.elleined.securityquestionapi.repository.PreDefinedQuestionRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class AfterStartUp {
    private final PreDefinedQuestionRepository preDefinedQuestionRepository;

    private final PreDefinedQuestionPopulator preDefinedQuestionPopulator;

    @PostConstruct
    void init() {
        if (preDefinedQuestionRepository.existsById(1)) {
            System.out.println("Returning because there's already saved questions");
            return;
        }

        System.out.println("Saving questions! Please wait...");
        preDefinedQuestionPopulator.populate();
        System.out.println("Saving questions success...");
    }
}
