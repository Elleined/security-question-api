package com.elleined.securityquestionapi.controller;

import com.elleined.securityquestionapi.model.Question;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.UserSecurityQuestion;
import com.elleined.securityquestionapi.service.question.QuestionService;
import com.elleined.securityquestionapi.service.user.UserService;
import com.elleined.securityquestionapi.service.usq.UserSecurityQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{currentUserId}/security-questions")
public class UserSecurityQuestionController  {
    private final UserSecurityQuestionService userSecurityQuestionService;

    private final UserService userService;

    private final QuestionService questionService;

    @GetMapping
    public List<UserSecurityQuestion> getAllByUser(@PathVariable("currentUserId") int currentUserId) {
        User currentUser = userService.getById(currentUserId);
        return userSecurityQuestionService.getAllByUser(currentUser);
    }

    @GetMapping("/{securityQuestionId}/check-answer")
    public boolean isAnswerCorrect(@PathVariable("currentUserId") int currentUserId,
                                   @PathVariable("securityQuestionId") int securityQuestionId,
                                   @RequestParam("providedAnswer") String providedAnswer) {

        User currentUser = userService.getById(currentUserId);
        UserSecurityQuestion userSecurityQuestion = userSecurityQuestionService.getById(securityQuestionId);
        return userSecurityQuestionService.isAnswerCorrect(currentUser, userSecurityQuestion, providedAnswer);
    }

    @PostMapping
    public UserSecurityQuestion save(@PathVariable("currentUserId") int currentUserId,
                                     @RequestParam("questionId") int questionId,
                                     @RequestParam("answer") String answer) {

        User currentUser = userService.getById(currentUserId);
        Question question = questionService.getById(questionId);
        return userSecurityQuestionService.save(currentUser, question, answer);
    }
}
