package com.elleined.securityquestionapi.controller;

import com.elleined.securityquestionapi.model.Question;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.SecurityQuestion;
import com.elleined.securityquestionapi.service.question.QuestionService;
import com.elleined.securityquestionapi.service.user.UserService;
import com.elleined.securityquestionapi.service.sq.SecurityQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{currentUserId}/security-questions")
public class SecurityQuestionController {
    private final SecurityQuestionService securityQuestionService;

    private final UserService userService;

    private final QuestionService questionService;

    @GetMapping
    public List<SecurityQuestion> getAllByUser(@PathVariable("currentUserId") int currentUserId) {
        User currentUser = userService.getById(currentUserId);
        return securityQuestionService.getAllByUser(currentUser);
    }

    @GetMapping("/{securityQuestionId}/check-answer")
    public boolean isAnswerCorrect(@PathVariable("currentUserId") int currentUserId,
                                   @PathVariable("securityQuestionId") int securityQuestionId,
                                   @RequestParam("providedAnswer") String providedAnswer) {

        User currentUser = userService.getById(currentUserId);
        SecurityQuestion securityQuestion = securityQuestionService.getById(securityQuestionId);
        return securityQuestionService.isAnswerCorrect(currentUser, securityQuestion, providedAnswer);
    }

    @PostMapping
    public SecurityQuestion save(@PathVariable("currentUserId") int currentUserId,
                                 @RequestParam("questionId") int questionId,
                                 @RequestParam("answer") String answer) {

        User currentUser = userService.getById(currentUserId);
        Question question = questionService.getById(questionId);
        return securityQuestionService.save(currentUser, question, answer);
    }
}
