package com.elleined.security_question_api.question;

import com.elleined.security_question_api.paging.PageRequest;
import com.elleined.security_question_api.paging.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/security-questions")
public class SecurityQuestionController {
    private final SecurityQuestionService securityQuestionService;

    public SecurityQuestionController(SecurityQuestionService securityQuestionService) {
        this.securityQuestionService = securityQuestionService;
    }

    @GetMapping
    public Pageable<SecurityQuestionDTO> getAll(@RequestParam(value = "name", required = false) String name,
                                                @RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                                @RequestParam(value = "size", defaultValue = "5", required = false) int size) {

        PageRequest request = PageRequest.of(page, size);
        int totalElements = securityQuestionService.getAllTotal(name);
        List<SecurityQuestionDTO> securityQuestions = securityQuestionService.getAll(name, request);

        return Pageable.of(securityQuestions, request, totalElements);

    }

    @PostMapping
    public void save(@RequestParam("name") String name) {
        securityQuestionService.save(name);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") UUID id,
                       @RequestParam("name") String name) {

        securityQuestionService.update(id, name);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") UUID id) {
        securityQuestionService.delete(id);
    }
}
