package com.elleined.securityquestionapi.populator;

import com.elleined.securityquestionapi.service.question.pdq.PreDefinedQuestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PreDefinedQuestionPopulatorTest {

    @Mock
    private PreDefinedQuestionService preDefinedQuestionService;

    @InjectMocks
    private PreDefinedQuestionPopulator preDefinedQuestionPopulator;

    @Test
    void populate() {
        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods
        when(preDefinedQuestionService.saveAll(anyList())).thenReturn(new ArrayList<>());

        // Calling the method
        preDefinedQuestionPopulator.populate();

        // Behavior Verifications
        verify(preDefinedQuestionService).saveAll(anyList());

        // Assertions
    }
}