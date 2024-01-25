package com.elleined.securityquestionapi.controller.question;

import com.elleined.securityquestionapi.mapper.QuestionMapper;
import com.elleined.securityquestionapi.service.question.cq.CustomQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{currentUserId}/custom-questions")
public class CustomQuestionController {
    private final CustomQuestionService customQuestionService;
    private final QuestionMapper questionMapper;

    // save
    // save all
    // get all
}
