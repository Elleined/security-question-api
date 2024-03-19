package com.elleined.securityquestionapi.mapper.question;

import com.elleined.securityquestionapi.dto.question.PreDefinedQuestionDTO;
import com.elleined.securityquestionapi.model.SecurityQuestion;
import com.elleined.securityquestionapi.model.question.PreDefinedQuestion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PreDefinedQuestionMapperTest {

    private final PreDefinedQuestionMapper preDefinedQuestionMapper = Mappers.getMapper(PreDefinedQuestionMapper.class);

    @Test
    void toEntity() {
        // Expected Value

        // Mock data
        String question = "Question";

        // Set up method

        // Stubbing methods

        // Calling the method
        PreDefinedQuestion preDefinedQuestion = preDefinedQuestionMapper.toEntity(question);

        // Behavior Verifications

        // Assertions
        assertEquals(0, preDefinedQuestion.getId());

        assertEquals(question, preDefinedQuestion.getQuestion());
        assertNotNull(preDefinedQuestion.getQuestion());

        assertNotNull(preDefinedQuestion.getSecurityQuestions());
        assertTrue(preDefinedQuestion.getSecurityQuestions().isEmpty());
    }

    @Test
    void toDTO() {
        // Expected Value

        // Mock data
        int id = 1;
        String question = "Question";
        List<SecurityQuestion> securityQuestions = new ArrayList<>();

        PreDefinedQuestion preDefinedQuestion = PreDefinedQuestion.preDefinedQuestionBuilder()
                .id(id)
                .question(question)
                .securityQuestions(securityQuestions)
                .build();

        // Set up method

        // Stubbing methods

        // Calling the method
        PreDefinedQuestionDTO dto = preDefinedQuestionMapper.toDTO(preDefinedQuestion);

        // Behavior Verifications

        // Assertions
        assertEquals(id, dto.getId());

        assertEquals(question, dto.getQuestion());
        assertNotNull(dto.getQuestion());

        assertNotNull(dto.getSecurityQuestionIds());
        assertTrue(dto.getSecurityQuestionIds().isEmpty());
    }
}