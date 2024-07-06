package com.elleined.securityquestionapi.controller.question;

import com.elleined.securityquestionapi.dto.question.CustomQuestionDTO;
import com.elleined.securityquestionapi.mapper.question.CustomQuestionMapper;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.sq.UserDefinedSecurityQuestion;
import com.elleined.securityquestionapi.service.question.cq.CustomQuestionService;
import com.elleined.securityquestionapi.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{currentUserId}/custom-questions")
public class CustomQuestionController {
    private final CustomQuestionService customQuestionService;
    private final CustomQuestionMapper customQuestionMapper;

    private final UserService userService;

    @PostMapping
    public CustomQuestionDTO save(@PathVariable("currentUserId") Integer currentUserId,
                                  @RequestParam("question") String question,
                                  @RequestParam("answer") String answer) {
        User currentUser = userService.getById(currentUserId);
        UserDefinedSecurityQuestion userDefinedQuestion = customQuestionService.save(currentUser, question, answer);
        return customQuestionMapper.toDTO(userDefinedQuestion);
    }

    @GetMapping("/{customQuestionId}/check-answer")
    public ResponseEntity<Boolean> isAnswerCorrect(@PathVariable("currentUserId") Integer currentUserId,
                                                   @PathVariable("customQuestionId") Integer customQuestionId,
                                                   @RequestParam("providedAnswer") String providedAnswer) {

        User currentUser = userService.getById(currentUserId);
        UserDefinedSecurityQuestion userDefinedQuestion = customQuestionService.getById(customQuestionId);
        return ResponseEntity.ok(customQuestionService.isAnswerCorrect(currentUser, userDefinedQuestion, providedAnswer));
    }

    @GetMapping
    public List<CustomQuestionDTO> getAll(@PathVariable("currentUserId") Integer currentUserId) {
        User currentUser = userService.getById(currentUserId);
        return customQuestionService.getAllByOwner(currentUser).stream()
                .map(customQuestionMapper::toDTO)
                .toList();
    }
}
