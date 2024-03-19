package com.elleined.securityquestionapi.mapper;

import com.elleined.securityquestionapi.dto.UserDTO;
import com.elleined.securityquestionapi.model.SecurityQuestion;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.question.CustomQuestion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Test
    void toDTO() {
        // Expected Value

        // Mock data
        int id = 1;
        String name = "Name";

        SecurityQuestion securityQuestion1 = SecurityQuestion.builder()
                .id(1)
                .build();

        SecurityQuestion securityQuestion2 = SecurityQuestion.builder()
                .id(2)
                .build();
        List<SecurityQuestion> securityQuestions = List.of(securityQuestion1, securityQuestion2);

        CustomQuestion customQuestion1 = CustomQuestion.customQuestionBuilder()
                .id(1)
                .build();

        CustomQuestion customQuestion2 = CustomQuestion.customQuestionBuilder()
                .id(2)
                .build();

        List<CustomQuestion> customQuestions = List.of(customQuestion1, customQuestion2);

        User owner = User.builder()
                .id(id)
                .name(name)
                .securityQuestions(securityQuestions)
                .customQuestions(customQuestions)
                .build();

        // Set up method
        List<Integer> securityQuestionIds = List.of(1, 2);
        List<Integer> customQuestionIds = List.of(1, 2);

        // Stubbing methods
        UserDTO dto = userMapper.toDTO(owner);

        // Calling the method

        // Behavior Verifications

        // Assertions
        assertEquals(id, dto.getId());

        assertEquals(name, dto.getName());
        assertNotNull(dto.getName());

        assertIterableEquals(securityQuestionIds, dto.getSecurityQuestionIds());
        assertIterableEquals(customQuestionIds, dto.getCustomQuestionIds());
    }

    @Test
    void toEntity() {
        // Expected Value

        // Mock data
        String name = "Name of User";

        // Set up method

        // Stubbing methods

        // Calling the method
        User user = userMapper.toEntity(name);

        // Behavior Verifications

        // Assertions
        assertEquals(0 , user.getId());
        assertEquals(name, user.getName());

        assertNotNull(user.getSecurityQuestions());
        assertNotNull(user.getCustomQuestions());
    }
}