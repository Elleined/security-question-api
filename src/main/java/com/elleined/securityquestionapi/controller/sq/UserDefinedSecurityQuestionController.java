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
@RequestMapping("/users/user-defined-security-questions")
public class UserDefinedSecurityQuestionController {
    private final UserService userService;

    private final UserDefinedSecurityQuestionService userDefinedSecurityQuestionService;
    private final UserDefinedSecurityQuestionMapper userDefinedSecurityQuestionMapper;

    @PostMapping
    public UserDefinedSecurityQuestionDTO save(@RequestHeader("Authorization") String jwt,
                                               @RequestParam("question") String question,
                                               @RequestParam("answer") String answer,
                                               @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        User currentUser = userService.getByJWT(jwt);
        UserDefinedSecurityQuestion userDefinedSecurityQuestion = userDefinedSecurityQuestionService.save(currentUser, question, answer);

        return userDefinedSecurityQuestionMapper.toDTO(userDefinedSecurityQuestion);
    }

    @GetMapping
    public Page<UserDefinedSecurityQuestionDTO> getAll(@RequestHeader("Authorization") String jwt,
                                                       @RequestParam(required = false, defaultValue = "1", value = "pageNumber") int pageNumber,
                                                       @RequestParam(required = false, defaultValue = "5", value = "pageSize") int pageSize,
                                                       @RequestParam(required = false, defaultValue = "ASC", value = "sortDirection") Sort.Direction direction,
                                                       @RequestParam(required = false, defaultValue = "id", value = "sortBy") String sortBy,
                                                       @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        User currentUser = userService.getByJWT(jwt);

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, direction, sortBy);
        return userDefinedSecurityQuestionService.getAll(currentUser, pageable)
                .map(userDefinedSecurityQuestionMapper::toDTO);
    }

    @GetMapping("/{securityQuestionId}/check-answer")
    public boolean isAnswerCorrect(@RequestHeader("Authorization") String jwt,
                                   @PathVariable("securityQuestionId") int securityQuestionId,
                                   @RequestParam("providedAnswer") String providedAnswer) {

        User currentUser = userService.getByJWT(jwt);
        UserDefinedSecurityQuestion securityQuestion = userDefinedSecurityQuestionService.getById(securityQuestionId);

        return userDefinedSecurityQuestionService.isAnswerCorrect(currentUser, securityQuestion, providedAnswer);
    }
}
