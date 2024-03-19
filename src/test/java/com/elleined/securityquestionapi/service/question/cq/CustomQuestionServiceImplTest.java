package com.elleined.securityquestionapi.service.question.cq;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.elleined.securityquestionapi.mapper.question.CustomQuestionMapper;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.question.CustomQuestion;
import com.elleined.securityquestionapi.repository.question.CustomQuestionRepository;
import jdk.dynalink.linker.LinkerServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CustomQuestionServiceImplTest {

    @Mock
    private CustomQuestionRepository customQuestionRepository;
    @Mock
    private CustomQuestionMapper customQuestionMapper;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CustomQuestionServiceImpl customQuestionService;

    @Test
    void existsById() {
        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods
        when(customQuestionRepository.existsById(anyInt())).thenReturn(true);

        // Calling the method
        boolean isExists = customQuestionService.existsById(anyInt());

        // Behavior Verifications
        verify(customQuestionRepository).existsById(anyInt());

        // Assertions
        assertTrue(isExists);
    }

    @Test
    void alreadyExists() {
        // Expected Value

        // Mock data
        CustomQuestion customQuestion1 = CustomQuestion.customQuestionBuilder()
                .question("Question 1")
                .build();

        CustomQuestion customQuestion2 = CustomQuestion.customQuestionBuilder()
                .question("Question 2")
                .build();

        CustomQuestion duplicatedQuestion = CustomQuestion.customQuestionBuilder()
                .question("Duplicated Question")
                .build();

        List<CustomQuestion> customQuestions = List.of(customQuestion1, customQuestion2, duplicatedQuestion);

        // Set up method

        // Stubbing methods
        when(customQuestionRepository.findAll()).thenReturn(customQuestions);

        // Calling the method
        boolean isExists = customQuestionService.alreadyExists("Duplicated Question");

        // Behavior Verifications
        verify(customQuestionRepository).findAll();

        // Assertions
        assertTrue(isExists);
    }

    @Test
    void save() {
        // Expected Value
        String encodedAnswer = "Encoded Answer";

        // Mock data
        User currentUser = new User();
        CustomQuestion expected = new CustomQuestion();

        // Set up method

        // Stubbing methods
        when(passwordEncoder.encode(anyString())).thenReturn(encodedAnswer);
        when(customQuestionMapper.toEntity(any(User.class), anyString(), anyString())).thenReturn(expected);
        when(customQuestionRepository.save(any(CustomQuestion.class))).thenReturn(expected);

        // Calling the method
        CustomQuestion actual = customQuestionService.save(currentUser, "Question", "Answer");

        // Behavior Verifications
        verify(passwordEncoder).encode(anyString());
        verify(customQuestionMapper).toEntity(any(User.class), anyString(), anyString());
        verify(customQuestionRepository).save(any(CustomQuestion.class));

        // Assertions
        assertEquals(expected, actual);
    }

    @Test
    void isAnswerCorrect() {
        // Expected Value
        String providedAnswer = "Provided Answer";
        String encodedAnswer = "Encoded Answer";

        // Mock data
        User currentUser = User.builder()
                .customQuestions(new ArrayList<>())
                .build();

        CustomQuestion customQuestion = CustomQuestion.customQuestionBuilder()
                .answer(encodedAnswer)
                .build();

        // Set up method
        currentUser.getCustomQuestions().add(customQuestion);

        // Stubbing methods
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);

        // Calling the method
        boolean isCorrect = customQuestionService.isAnswerCorrect(currentUser, customQuestion, providedAnswer);

        // Behavior Verifications
        verify(passwordEncoder).matches(anyString(), anyString());

        // Assertions
        assertTrue(isCorrect);
    }

    @Test
    void getAllByOwner() {
        // Expected Value

        // Mock data
        CustomQuestion customQuestion1 = CustomQuestion.customQuestionBuilder()
                .id(1)
                .build();

        CustomQuestion customQuestion2 = CustomQuestion.customQuestionBuilder()
                .id(2)
                .build();

        User currentUser = User.builder()
                .customQuestions(List.of(customQuestion2, customQuestion1))
                .build();

        // Set up method
        List<CustomQuestion> expected = List.of(customQuestion1, customQuestion2);

        // Stubbing methods

        // Calling the method
        List<CustomQuestion> actual = customQuestionService.getAllByOwner(currentUser);

        // Behavior Verifications

        // Assertions
        assertIterableEquals(expected, actual);
    }
}