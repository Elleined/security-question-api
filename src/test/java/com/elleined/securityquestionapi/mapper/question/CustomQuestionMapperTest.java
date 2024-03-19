package com.elleined.securityquestionapi.mapper.question;

import com.elleined.securityquestionapi.dto.question.CustomQuestionDTO;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.question.CustomQuestion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CustomQuestionMapperTest {

    CustomQuestionMapper customQuestionMapper = Mappers.getMapper(CustomQuestionMapper.class);

    @Test
    void toEntity() {
        // Expected Value

        // Mock data
        User owner = new User();
        String question = "Question";
        String answer = "Answer";

        // Set up method

        // Stubbing methods

        // Calling the method
        CustomQuestion customQuestion = customQuestionMapper.toEntity(owner, question, answer);

        // Behavior Verifications

        // Assertions
        assertEquals(0, customQuestion.getId());

        assertEquals(question, customQuestion.getQuestion());
        assertNotNull(customQuestion.getQuestion());

        assertEquals(owner, customQuestion.getOwner());
        assertNotNull(customQuestion.getOwner());

        assertNotNull(customQuestion.getCreatedAt());

        assertEquals(answer, customQuestion.getAnswer());
        assertNotNull(customQuestion.getAnswer());
    }

    @Test
    void toDTO() {
        // Expected Value

        // Mock data

        int ownerId = 2;
        User owner = User.builder()
                .id(ownerId)
                .build();

        int id = 1;
        String question = "Question";
        String answer = "Answer";
        LocalDateTime dateTime = LocalDateTime.now().minusMinutes(1);

        CustomQuestion customQuestion = CustomQuestion.customQuestionBuilder()
                .id(id)
                .question(question)
                .answer(answer)
                .createdAt(dateTime)
                .owner(owner)
                .build();

        // Set up method

        // Stubbing methods
        CustomQuestionDTO dto = customQuestionMapper.toDTO(customQuestion);

        // Calling the method

        // Behavior Verifications

        // Assertions
        assertEquals(id, dto.getId());

        assertEquals(question, dto.getQuestion());
        assertNotNull(dto.getQuestion());

        assertEquals(ownerId, dto.getOwnerId());

        assertNotNull(dto.getCreatedAt());

        assertEquals(answer, dto.getAnswer());
        assertNotNull(dto.getAnswer());
    }
}