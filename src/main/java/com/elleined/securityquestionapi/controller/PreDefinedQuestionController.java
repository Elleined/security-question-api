package com.elleined.securityquestionapi.controller;

import com.elleined.securityquestionapi.dto.PreDefinedQuestionDTO;
import com.elleined.securityquestionapi.mapper.PreDefinedQuestionMapper;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.service.PreDefinedQuestionService;
import com.elleined.securityquestionapi.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{currentUserId}/pre-defined-questions")
public class PreDefinedQuestionController {
    private final UserService userService;

    private final PreDefinedQuestionService preDefinedQuestionService;
    private final PreDefinedQuestionMapper preDefinedQuestionMapper;

    @GetMapping
    public Page<PreDefinedQuestionDTO> getAll(@PathVariable("currentUserId") int currentUserId,
                                              @RequestParam(required = false, defaultValue = "1", value = "pageNumber") int pageNumber,
                                              @RequestParam(required = false, defaultValue = "5", value = "pageSize") int pageSize,
                                              @RequestParam(required = false, defaultValue = "ASC", value = "sortDirection") Sort.Direction direction,
                                              @RequestParam(required = false, defaultValue = "id", value = "sortBy") String sortBy,
                                              @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        User currentUser = userService.getById(currentUserId);

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, direction, sortBy);
        return preDefinedQuestionService.getAll(pageable)
                .map(preDefinedQuestionMapper::toDTO)
                .map(dto -> dto.addLinks(currentUser, includeRelatedLinks));
    }
}
