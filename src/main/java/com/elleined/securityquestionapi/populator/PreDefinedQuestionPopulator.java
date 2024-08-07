package com.elleined.securityquestionapi.populator;

import com.elleined.securityquestionapi.service.PreDefinedQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PreDefinedQuestionPopulator implements Populator {
    private final PreDefinedQuestionService preDefinedQuestionService;

    @Override
    public void populate() {
        List<String> questions = List.of(
                "What was the house number and street name you lived in as a child",
                "What were the last four digits of your childhood telephone number",
                "What primary school did you attend",
                "In what town or city did you meet your spouse/partner",
                "What was the name of your oldest child",
                "What are the last five digits of your driver's license number",
                "What is your grandmother(on mother's side) maiden name",
                "What is your spouse or partner's mother's maiden name",
                "In what town did your parents meet",
                "What time of the day were you born(hh:mm)",
                "What time of the day was your first child born(hh:mm)"
        );
        preDefinedQuestionService.saveAll(questions);
    }
}
