package com.elleined.security_question_api.user_defined_security_question;

import com.elleined.security_question_api.exception.SecurityQuestionAPIException;
import com.elleined.security_question_api.paging.PageRequest;
import com.elleined.security_question_api.paging.Pageable;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user-defined-security-questions")
public class UserDefinedSecurityQuestionController {
    private final UserDefinedSecurityQuestionService userDefinedSecurityQuestionService;

    public UserDefinedSecurityQuestionController(UserDefinedSecurityQuestionService userDefinedSecurityQuestionService) {
        this.userDefinedSecurityQuestionService = userDefinedSecurityQuestionService;
    }

    @GetMapping
    public Pageable<UserDefinedSecurityQuestionDTO> getAll(@RequestParam(value = "resource_id", required = false) UUID resourceId,
                                                           @RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                                           @RequestParam(value = "size", defaultValue = "5", required = false) int size) {

        PageRequest request = PageRequest.of(page, size);
        int totalElements = userDefinedSecurityQuestionService.getAllTotal(resourceId);
        List<UserDefinedSecurityQuestionDTO> userdefinedSecurityQuestions = userDefinedSecurityQuestionService.getAll(resourceId, request);

        return Pageable.of(userdefinedSecurityQuestions, request, totalElements);
    }

    @PostMapping
    public void save(@RequestParam("resource_id") UUID resourceId,
                     @RequestParam("question") String question,
                     @RequestParam("answer") String answer) throws SecurityQuestionAPIException {

        userDefinedSecurityQuestionService.save(resourceId, question, answer);
    }

    @PatchMapping
    public void updateAnswer(@Valid @RequestBody UserDefinedSecurityQuestionRequest request) throws SecurityQuestionAPIException {
        userDefinedSecurityQuestionService.updateAnswer(request);
    }

    @GetMapping("/is-answer-correct")
    public boolean isAnswerCorrect(@Valid @RequestBody UserDefinedSecurityQuestionRequest request) {
        return userDefinedSecurityQuestionService.isAnswerCorrect(request);
    }
}
