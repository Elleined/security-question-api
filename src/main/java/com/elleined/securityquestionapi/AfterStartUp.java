package com.elleined.securityquestionapi;

import com.elleined.securityquestionapi.populator.PreDefinedQuestionPopulator;
import com.elleined.securityquestionapi.populator.UserPopulator;
import com.elleined.securityquestionapi.repository.PreDefinedQuestionRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class AfterStartUp {
    private final PreDefinedQuestionRepository preDefinedQuestionRepository;

    private final PreDefinedQuestionPopulator preDefinedQuestionPopulator;
    private final UserPopulator userPopulator;

    @PostConstruct
    void init() {
        if (preDefinedQuestionRepository.existsById(1)) {
            System.out.println("Returning because pre-defined data is already saved.");
            return;
        }

        System.out.println("Saving pre-defined data! Please wait...");
        userPopulator.populate();
        preDefinedQuestionPopulator.populate();
        System.out.println("Saving pre-defined data success...");
    }
}
