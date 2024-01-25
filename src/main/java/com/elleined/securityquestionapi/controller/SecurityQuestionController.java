package com.elleined.securityquestionapi.controller;

import com.elleined.securityquestionapi.dto.SecurityQuestionDTO;
import com.elleined.securityquestionapi.mapper.SecurityQuestionMapper;
import com.elleined.securityquestionapi.model.SecurityQuestion;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.question.PreDefinedQuestion;
import com.elleined.securityquestionapi.service.question.pdq.PreDefinedQuestionService;
import com.elleined.securityquestionapi.service.sq.SecurityQuestionService;
import com.elleined.securityquestionapi.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{currentUserId}/security-questions")
public class SecurityQuestionController {
    private final SecurityQuestionService securityQuestionService;
    private final SecurityQuestionMapper securityQuestionMapper;

    private final UserService userService;

    private final PreDefinedQuestionService preDefinedQuestionService;

    @GetMapping
    public List<SecurityQuestionDTO> getAllByUser(@PathVariable("currentUserId") int currentUserId) {
        User currentUser = userService.getById(currentUserId);
        return securityQuestionService.getAllByUser(currentUser).stream()
                .map(securityQuestionMapper::toDTO)
                .toList();
    }

    @GetMapping("/{securityQuestionId}/check-answer")
    public ResponseEntity<Boolean> isAnswerCorrect(@PathVariable("currentUserId") int currentUserId,
                                                   @PathVariable("securityQuestionId") Integer securityQuestionId,
                                                   @RequestParam("providedAnswer") String providedAnswer) {

        User currentUser = userService.getById(currentUserId);
        SecurityQuestion securityQuestion = securityQuestionService.getById(securityQuestionId);
        return ResponseEntity.ok(securityQuestionService.isAnswerCorrect(currentUser, securityQuestion, providedAnswer));
    }

    @PostMapping
    public SecurityQuestionDTO save(@PathVariable("currentUserId") Integer currentUserId,
                                    @RequestParam("questionId") Integer questionId,
                                    @RequestParam("answer") String answer) {

        User currentUser = userService.getById(currentUserId);
        PreDefinedQuestion preDefinedQuestion = preDefinedQuestionService.getById(questionId);
        SecurityQuestion securityQuestion = securityQuestionService.save(currentUser, preDefinedQuestion, answer);
        return securityQuestionMapper.toDTO(securityQuestion);
    }
}
