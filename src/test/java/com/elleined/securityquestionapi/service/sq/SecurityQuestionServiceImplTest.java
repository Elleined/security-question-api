package com.elleined.securityquestionapi.service.sq;

import com.elleined.securityquestionapi.mapper.SecurityQuestionMapper;
import com.elleined.securityquestionapi.model.SecurityQuestion;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.question.PreDefinedQuestion;
import com.elleined.securityquestionapi.repository.SecurityQuestionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SecurityQuestionServiceImplTest {

    @Mock
    private SecurityQuestionRepository securityQuestionRepository;
    @Mock
    private SecurityQuestionMapper securityQuestionMapper;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private SecurityQuestionServiceImpl securityQuestionService;

    @Test
    void getAllByUser() {
        // Expected Value

        // Mock data
        SecurityQuestion securityQuestion1 = SecurityQuestion.builder()
                .id(1)
                .build();

        SecurityQuestion securityQuestion2 = SecurityQuestion.builder()
                .id(2)
                .build();

        User currentUser = User.builder()
                .securityQuestions(List.of(securityQuestion2, securityQuestion1))
                .build();

        // Set up method
        List<SecurityQuestion> expected = List.of(securityQuestion1, securityQuestion2);

        // Stubbing methods

        // Calling the method
        List<SecurityQuestion> actual = securityQuestionService.getAllByUser(currentUser);

        // Behavior Verifications

        // Assertions
        assertIterableEquals(expected, actual);
    }

    @Test
    void isAnswerCorrect() {
        // Expected Value
        String providedAnswer = "Provided Answer";
        String encodedAnswer = "Encoded Answer";

        // Mock data
        User currentUser = User.builder()
                .securityQuestions(new ArrayList<>())
                .build();

        SecurityQuestion securityQuestion = SecurityQuestion.builder()
                .answer(encodedAnswer)
                .build();

        // Set up method
        currentUser.getSecurityQuestions().add(securityQuestion);

        // Stubbing methods
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);

        // Calling the method
        boolean isCorrect = securityQuestionService.isAnswerCorrect(currentUser, securityQuestion, providedAnswer);

        // Behavior Verifications
        verify(passwordEncoder).matches(anyString(), anyString());

        // Assertions
        assertTrue(isCorrect);
    }

    @Test
    void save() {
        // Expected Value
        String encodedAnswer = "Encoded Answer";

        // Mock data
        User currentUser = User.builder()
                .securityQuestions(new ArrayList<>())
                .build();

        SecurityQuestion expected = new SecurityQuestion();

        // Set up method

        // Stubbing methods
        when(passwordEncoder.encode(anyString())).thenReturn(encodedAnswer);
        when(securityQuestionMapper.toEntity(any(User.class), any(PreDefinedQuestion.class), anyString())).thenReturn(expected);
        when(securityQuestionRepository.save(any(SecurityQuestion.class))).thenReturn(expected);

        // Calling the method
        SecurityQuestion actual = securityQuestionService.save(currentUser, new PreDefinedQuestion(), "Answer");

        // Behavior Verifications
        verify(passwordEncoder).encode(anyString());
        verify(securityQuestionMapper).toEntity(any(User.class), any(PreDefinedQuestion.class), anyString());
        verify(securityQuestionRepository).save(any(SecurityQuestion.class));

        // Assertions
        assertEquals(expected, actual);
    }
}