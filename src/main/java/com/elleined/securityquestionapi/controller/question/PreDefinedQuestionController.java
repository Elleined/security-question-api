package com.elleined.securityquestionapi.controller.question;

import com.elleined.securityquestionapi.dto.PreDefinedQuestionDTO;
import com.elleined.securityquestionapi.mapper.sq.PreDefinedSecurityQuestionMapper;
import com.elleined.securityquestionapi.model.PreDefinedQuestion;
import com.elleined.securityquestionapi.service.pdq.PreDefinedQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/predefined-questions")
public class PreDefinedQuestionController {
    private final PreDefinedQuestionService preDefinedQuestionService;
    private final PreDefinedSecurityQuestionMapper preDefinedSecurityQuestionMapper;

    @GetMapping
    public List<PreDefinedQuestionDTO> getAll() {
        return preDefinedQuestionService.getAll().stream()
                .map(preDefinedSecurityQuestionMapper::toDTO)
                .toList();
    }

    @GetMapping("/{questionId}")
    public PreDefinedQuestionDTO getById(@PathVariable("questionId") int questionId) {
        PreDefinedQuestion preDefinedQuestion = preDefinedQuestionService.getById(questionId);
        return preDefinedSecurityQuestionMapper.toDTO(preDefinedQuestion);
    }
}
