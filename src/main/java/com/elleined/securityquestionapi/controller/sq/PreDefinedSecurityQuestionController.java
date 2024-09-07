package com.elleined.securityquestionapi.controller.sq;

import com.elleined.securityquestionapi.dto.sq.PreDefinedSecurityQuestionDTO;
import com.elleined.securityquestionapi.mapper.sq.PreDefinedSecurityQuestionMapper;
import com.elleined.securityquestionapi.model.PreDefinedQuestion;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.sq.PreDefinedSecurityQuestion;
import com.elleined.securityquestionapi.service.PreDefinedQuestionService;
import com.elleined.securityquestionapi.service.sq.pdsq.PreDefinedSecurityQuestionService;
import com.elleined.securityquestionapi.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/pre-defined-security-questions")
public class PreDefinedSecurityQuestionController {
    private final UserService userService;

    private final PreDefinedSecurityQuestionService preDefinedSecurityQuestionService;
    private final PreDefinedSecurityQuestionMapper preDefinedSecurityQuestionMapper;

    private final PreDefinedQuestionService preDefinedQuestionService;

    @PostMapping
    public PreDefinedSecurityQuestionDTO save(@RequestHeader("Authorization") String jwt,
                                              @RequestParam("preDefinedQuestionId") int preDefinedQuestionId,
                                              @RequestParam("answer") String answer,
                                              @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        User currentUser = userService.getByJWT(jwt);
        PreDefinedQuestion preDefinedQuestion = preDefinedQuestionService.getById(preDefinedQuestionId);

        PreDefinedSecurityQuestion preDefinedSecurityQuestion = preDefinedSecurityQuestionService.save(currentUser, preDefinedQuestion, answer);

        return preDefinedSecurityQuestionMapper.toDTO(preDefinedSecurityQuestion);
    }

    @GetMapping
    public Page<PreDefinedSecurityQuestionDTO> getAll(@RequestHeader("Authorization") String jwt,
                                                      @RequestParam(required = false, defaultValue = "1", value = "pageNumber") int pageNumber,
                                                      @RequestParam(required = false, defaultValue = "5", value = "pageSize") int pageSize,
                                                      @RequestParam(required = false, defaultValue = "ASC", value = "sortDirection") Sort.Direction direction,
                                                      @RequestParam(required = false, defaultValue = "id", value = "sortBy") String sortBy,
                                                      @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        User currentUser = userService.getByJWT(jwt);

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, direction, sortBy);
        return preDefinedSecurityQuestionService.getAll(currentUser, pageable)
                .map(preDefinedSecurityQuestionMapper::toDTO);
    }

    @GetMapping("/{securityQuestionId}/check-answer")
    public boolean isAnswerCorrect(@RequestHeader("Authorization") String jwt,
                                   @PathVariable("securityQuestionId") int securityQuestionId,
                                   @RequestParam("providedAnswer") String providedAnswer) {

        User currentUser = userService.getByJWT(jwt);
        PreDefinedSecurityQuestion securityQuestion = preDefinedSecurityQuestionService.getById(securityQuestionId);

        return preDefinedSecurityQuestionService.isAnswerCorrect(currentUser, securityQuestion, providedAnswer);
    }
}
