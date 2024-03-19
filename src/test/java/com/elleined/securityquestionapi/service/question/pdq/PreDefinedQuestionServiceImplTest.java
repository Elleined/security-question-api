package com.elleined.securityquestionapi.service.question.pdq;

import com.elleined.securityquestionapi.mapper.question.PreDefinedQuestionMapper;
import com.elleined.securityquestionapi.model.question.PreDefinedQuestion;
import com.elleined.securityquestionapi.repository.question.PreDefinedQuestionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PreDefinedQuestionServiceImplTest {

    @Mock
    private PreDefinedQuestionRepository preDefinedQuestionRepository;
    @Mock
    private PreDefinedQuestionMapper predefinedQuestionMapper;

    @InjectMocks
    private PreDefinedQuestionServiceImpl preDefinedQuestionService;

    @Test
    void existsById() {
        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods
        when(preDefinedQuestionRepository.existsById(anyInt())).thenReturn(true);

        // Calling the method
        boolean isExists = preDefinedQuestionService.existsById(anyInt());

        // Behavior Verifications
        verify(preDefinedQuestionRepository).existsById(anyInt());

        // Assertions
        assertTrue(isExists);
    }

    @Test
    void alreadyExists() {
        // Expected Value

        // Mock data
        PreDefinedQuestion preDefinedQuestion1 = PreDefinedQuestion.preDefinedQuestionBuilder()
                .question("Question 1")
                .build();

        PreDefinedQuestion preDefinedQuestion2 = PreDefinedQuestion.preDefinedQuestionBuilder()
                .question("Question 2")
                .build();

        PreDefinedQuestion duplicatedQuestion = PreDefinedQuestion.preDefinedQuestionBuilder()
                .question("Duplicated Question")
                .build();

        List<PreDefinedQuestion> preDefinedQuestions = List.of(preDefinedQuestion1, preDefinedQuestion2, duplicatedQuestion);

        // Set up method

        // Stubbing methods
        when(preDefinedQuestionRepository.findAll()).thenReturn(preDefinedQuestions);

        // Calling the method
        boolean isExists = preDefinedQuestionService.alreadyExists("Duplicated Question");

        // Behavior Verifications
        verify(preDefinedQuestionRepository).findAll();

        // Assertions
        assertTrue(isExists);
    }

    @Test
    void getAll() {
        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method

        // Behavior Verifications

        // Assertions
    }

    @Test
    void save() {
        // Expected Value

        // Mock data
        PreDefinedQuestion expected = new PreDefinedQuestion();

        // Set up method

        // Stubbing methods
        when(predefinedQuestionMapper.toEntity(anyString())).thenReturn(expected);
        when(preDefinedQuestionRepository.save(any(PreDefinedQuestion.class))).thenReturn(expected);

        // Calling the method
        PreDefinedQuestion actual = preDefinedQuestionService.save("Pre Defined Question");

        // Behavior Verifications
        verify(predefinedQuestionMapper).toEntity(anyString());
        verify(preDefinedQuestionRepository).save(any(PreDefinedQuestion.class));

        // Assertions
        assertEquals(expected, actual);
    }

    @Test
    void saveAll() {
        // Expected Value

        // Mock data
        PreDefinedQuestion preDefinedQuestion = new PreDefinedQuestion();
        List<String> questions = List.of("Question 1", "Question 2");
        List<PreDefinedQuestion> expected = List.of();

        // Set up method

        // Stubbing methods
        when(predefinedQuestionMapper.toEntity(anyString())).thenReturn(preDefinedQuestion);
        when(preDefinedQuestionRepository.saveAll(anyList())).thenReturn(expected);

        // Calling the method
        List<PreDefinedQuestion> actual = preDefinedQuestionService.saveAll(questions);

        // Behavior Verifications
        verify(predefinedQuestionMapper, atLeastOnce()).toEntity(anyString());
        verify(preDefinedQuestionRepository).saveAll(anyList());

        // Assertions
        assertFalse(actual.isEmpty());
    }

    @Test
    void getAllByOwner() {
        // Expected Value

        // Mock data
        PreDefinedQuestion preDefinedQuestion1 = PreDefinedQuestion.preDefinedQuestionBuilder()
                .id(1)
                .build();

        PreDefinedQuestion preDefinedQuestion2 = PreDefinedQuestion.preDefinedQuestionBuilder()
                .id(2)
                .build();


        // Set up method
        List<PreDefinedQuestion> expected = List.of(preDefinedQuestion1, preDefinedQuestion2);

        // Stubbing methods
        when(preDefinedQuestionRepository.findAll()).thenReturn(expected);

        // Calling the method
        List<PreDefinedQuestion> actual = preDefinedQuestionService.getAll();

        // Behavior Verifications

        // Assertions
        assertIterableEquals(expected, actual);
    }
}