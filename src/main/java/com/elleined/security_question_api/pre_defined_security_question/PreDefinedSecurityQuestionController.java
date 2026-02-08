package com.elleined.security_question_api.pre_defined_security_question;

import com.elleined.security_question_api.exception.SecurityQuestionAPIException;
import com.elleined.security_question_api.paging.PageRequest;
import com.elleined.security_question_api.paging.Pageable;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/predefined-security-questions")
public class PreDefinedSecurityQuestionController {
    private final PreDefinedSecurityQuestionService preDefinedSecurityQuestionService;

    public PreDefinedSecurityQuestionController(PreDefinedSecurityQuestionService preDefinedSecurityQuestionService) {
        this.preDefinedSecurityQuestionService = preDefinedSecurityQuestionService;
    }

    @GetMapping
    public Pageable<PreDefinedSecurityQuestionDTO> getAll(@RequestParam("resource_id") UUID resourceId,
                                                          @RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                                          @RequestParam(value = "size", defaultValue = "5", required = false) int size) {

        PageRequest request = PageRequest.of(page, size);
        int totalElements = preDefinedSecurityQuestionService.getAllTotal(resourceId);
        List<PreDefinedSecurityQuestionDTO> predefinedSecurityQuestions = preDefinedSecurityQuestionService.getAll(resourceId, request);

        return Pageable.of(predefinedSecurityQuestions, request, totalElements);
    }

    @PostMapping
    public void save(@Valid @RequestBody PreDefinedSecurityQuestionRequest request) throws SecurityQuestionAPIException {
        preDefinedSecurityQuestionService.save(request);
    }

    @PatchMapping
    public void updateAnswer(@Valid @RequestBody PreDefinedSecurityQuestionRequest request) throws SecurityQuestionAPIException {
        preDefinedSecurityQuestionService.updateAnswer(request);
    }

    @PostMapping("/is-answer-correct")
    public boolean isAnswerCorrect(@Valid @RequestBody PreDefinedSecurityQuestionRequest request) {
        return preDefinedSecurityQuestionService.isAnswerCorrect(request);
    }
}
