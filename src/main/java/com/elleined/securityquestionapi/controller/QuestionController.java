package com.elleined.securityquestionapi.controller;

import com.elleined.securityquestionapi.dto.QuestionDTO;
import com.elleined.securityquestionapi.mapper.QuestionMapper;
import com.elleined.securityquestionapi.model.Question;
import com.elleined.securityquestionapi.service.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionMapper questionMapper;

    @PostMapping
    public QuestionDTO save(@RequestParam("question") String question) {
        Question savedQuestion = questionService.save(question);
        return questionMapper.toDTO(savedQuestion);
    }

    @GetMapping
    public List<QuestionDTO> getAll() {
        return questionService.getAll().stream()
                .map(questionMapper::toDTO)
                .toList();
    }
}
