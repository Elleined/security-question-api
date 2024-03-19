package com.elleined.securityquestionapi.mapper;

import com.elleined.securityquestionapi.dto.SecurityQuestionDTO;
import com.elleined.securityquestionapi.model.SecurityQuestion;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.question.PreDefinedQuestion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class SecurityQuestionMapperTest {

    private final SecurityQuestionMapper securityQuestionMapper = Mappers.getMapper(SecurityQuestionMapper.class);

    @Test
    void toEntity() {
        // Expected Value

        // Mock data
        User currentUser = new User();
        PreDefinedQuestion preDefinedQuestion = new PreDefinedQuestion();
        String answer = "Answer";


        // Set up method

        // Stubbing methods

        // Calling the method
        SecurityQuestion securityQuestion = securityQuestionMapper.toEntity(currentUser, preDefinedQuestion, answer);

        // Behavior Verifications

        // Assertions
        assertEquals(0, securityQuestion.getId());

        assertNotNull(securityQuestion.getCreatedAt());

        assertEquals(answer, securityQuestion.getAnswer());
        assertNotNull(securityQuestion.getAnswer());

        assertEquals(currentUser, securityQuestion.getOwner());
        assertNotNull(securityQuestion.getOwner());

        assertEquals(preDefinedQuestion, securityQuestion.getPreDefinedQuestion());
        assertNotNull(securityQuestion.getPreDefinedQuestion());
    }

    @Test
    void toDTO() {
        // Expected Value

        // Mock data
        int id = 0;
        LocalDateTime dateTime = LocalDateTime.now().plusMinutes(1);
        String answer = "Answer";

        int currentUserId = 1;
        User currentUser = User.builder()
                .id(currentUserId)
                .build();

        int preDefinedQuestionId = 1;
        PreDefinedQuestion preDefinedQuestion = PreDefinedQuestion.preDefinedQuestionBuilder()
                .id(preDefinedQuestionId)
                .build();

        SecurityQuestion securityQuestion = SecurityQuestion.builder()
                .id(id)
                .createdAt(dateTime)
                .answer(answer)
                .owner(currentUser)
                .preDefinedQuestion(preDefinedQuestion)
                .build();

        // Set up method

        // Stubbing methods
        SecurityQuestionDTO dto = securityQuestionMapper.toDTO(securityQuestion);

        // Calling the method

        // Behavior Verifications

        // Assertions
        assertEquals(id, dto.getId());

        assertEquals(dateTime, dto.getCreatedAt());
        assertNotNull(dto.getCreatedAt());

        assertEquals(answer, dto.getAnswer());
        assertNotNull(dto.getAnswer());

        assertEquals(currentUserId, dto.getOwnerId());

        assertEquals(preDefinedQuestionId, dto.getPreDefinedQuestionId());
    }
}