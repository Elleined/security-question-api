package com.elleined.securityquestionapi.controller.question;

import com.elleined.securityquestionapi.dto.question.QuestionDTO;
import com.elleined.securityquestionapi.service.question.pdq.PreDefinedQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/predefined-questions")
public class PreDefinedQuestionController {
    private final PreDefinedQuestionService preDefinedQuestionService;
    private final QuestionMapper questionMapper;

    @GetMapping
    public List<QuestionDTO> getAll() {
        return preDefinedQuestionService.getAll().stream()
                .map(questionMapper::toDTO)
                .toList();
    }
}
