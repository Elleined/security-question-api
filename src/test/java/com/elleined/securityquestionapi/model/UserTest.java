package com.elleined.securityquestionapi.model;

import com.elleined.securityquestionapi.model.question.CustomQuestion;
import com.elleined.securityquestionapi.service.sq.SecurityQuestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class UserTest {

    @Test
    void hasSecurityQuestion() {
        // Expected Value

        // Mock data
        SecurityQuestion ownedSecurityQuestion = new SecurityQuestion();
        SecurityQuestion notOwnedSecurityQuestion = new SecurityQuestion();
        User currentUser = User.builder()
                .securityQuestions(List.of(ownedSecurityQuestion, notOwnedSecurityQuestion))
                .build();

        // Set up method

        // Stubbing methods

        // Calling the method
        boolean has = currentUser.has(ownedSecurityQuestion);

        // Behavior Verifications

        // Assertions
        assertTrue(has);
    }

    @Test
    void hasCustomQuestion() {
        // Expected Value

        // Mock data
        CustomQuestion ownedCustomQuestion = new CustomQuestion();
        CustomQuestion notOwnedCustomQuestion = new CustomQuestion();
        User currentUser = User.builder()
                .customQuestions(List.of(ownedCustomQuestion, notOwnedCustomQuestion))
                .build();

        // Set up method

        // Stubbing methods

        // Calling the method
        boolean has = currentUser.has(ownedCustomQuestion);

        // Behavior Verifications

        // Assertions
        assertTrue(has);
    }

    @Test
    void hasReachedLimitOfSecurityQuestions() {
        // Expected Value

        // Mock data
        User currentUser = User.builder()
                .securityQuestions(List.of(new SecurityQuestion(), new SecurityQuestion(), new SecurityQuestion()))
                .build();

        // Set up method

        // Stubbing methods

        // Calling the method
        boolean hasReachedLimitOfSecurityQuestions = currentUser.hasReachedLimitOfSecurityQuestions();

        // Behavior Verifications

        // Assertions
        assertTrue(hasReachedLimitOfSecurityQuestions);
        assertEquals(SecurityQuestionService.SECURITY_QUESTION_LIMIT, currentUser.getSecurityQuestions().size());
    }
}