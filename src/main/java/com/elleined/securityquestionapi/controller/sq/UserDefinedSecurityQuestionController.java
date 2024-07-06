package com.elleined.securityquestionapi.controller.sq;

import com.elleined.securityquestionapi.dto.sq.UserDefinedSecurityQuestionDTO;
import com.elleined.securityquestionapi.mapper.sq.UserDefinedSecurityQuestionMapper;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.sq.UserDefinedSecurityQuestion;
import com.elleined.securityquestionapi.service.sq.udsq.UserDefinedSecurityQuestionService;
import com.elleined.securityquestionapi.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{currentUserId}/user-defined-security-questions")
public class UserDefinedSecurityQuestionController {
    private final UserService userService;

    private final UserDefinedSecurityQuestionService userDefinedSecurityQuestionService;
    private final UserDefinedSecurityQuestionMapper userDefinedSecurityQuestionMapper;

    @PostMapping
    public UserDefinedSecurityQuestionDTO save(@PathVariable("currentUserId") int currentUserId,
                                               @RequestParam("question") String question,
                                               @RequestParam("answer") String answer,
                                               @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        User currentUser = userService.getById(currentUserId);
        UserDefinedSecurityQuestion userDefinedSecurityQuestion = userDefinedSecurityQuestionService.save(currentUser, question, answer);

        return userDefinedSecurityQuestionMapper.toDTO(userDefinedSecurityQuestion)
                .addLinks(currentUser, includeRelatedLinks);
    }

    @GetMapping
    public Page<UserDefinedSecurityQuestionDTO> getAll(@PathVariable("currentUserId") int currentUserId,
                                                       @RequestParam(required = false, defaultValue = "1", value = "pageNumber") int pageNumber,
                                                       @RequestParam(required = false, defaultValue = "5", value = "pageSize") int pageSize,
                                                       @RequestParam(required = false, defaultValue = "ASC", value = "sortDirection") Sort.Direction direction,
                                                       @RequestParam(required = false, defaultValue = "id", value = "sortBy") String sortBy,
                                                       @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        User currentUser = userService.getById(currentUserId);

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, direction, sortBy);
        return userDefinedSecurityQuestionService.getAll(currentUser, pageable)
                .map(userDefinedSecurityQuestionMapper::toDTO)
                .map(dto -> dto.addLinks(currentUser, includeRelatedLinks));
    }

    @GetMapping("/{securityQuestionId}")
    public boolean isAnswerCorrect(@PathVariable("currentUserId") int currentUserId,
                                   @PathVariable("securityQuestionId") int securityQuestionId,
                                   @RequestParam("providedAnswer") String providedAnswer) {

        User currentUser = userService.getById(currentUserId);
        UserDefinedSecurityQuestion securityQuestion = userDefinedSecurityQuestionService.getById(securityQuestionId);

        return userDefinedSecurityQuestionService.isAnswerCorrect(currentUser, securityQuestion, providedAnswer);
    }
}
