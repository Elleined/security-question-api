package com.elleined.securityquestionapi.controller.question;

import com.elleined.securityquestionapi.dto.question.CustomQuestionDTO;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.service.question.cq.CustomQuestionService;
import com.elleined.securityquestionapi.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{currentUserId}/custom-questions")
public class CustomQuestionController {
    private final CustomQuestionService customQuestionService;
    private final QuestionMapper questionMapper;

    private final UserService userService;
    // save
    // get all

    @PostMapping
    public CustomQuestionDTO save(@PathVariable("currentUserId") int currentUserId,
                                  @RequestParam("question") String question) {
        User currentUser = userService.getById(currentUserId);
        CustomQuestionDTO customQuestion = customQuestionService.save(currentUser, question);
        return questionMapper.toDTO(customQuestion);
    }

    @GetMapping
    public List<CustomQuestionDTO> getAll(@PathVariable("currentUserId") int currentUserId) {
        User currentUser = userService.getById(currentUserId);
        return customQuestionService.getAllByOwner(currentUser).stream()
                .map(questionMapper::toDTO)
                .toList();
    }
}
