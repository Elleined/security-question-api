package com.elleined.securityquestionapi.service;

import com.elleined.securityquestionapi.exception.resource.ResourceNotFoundException;
import com.elleined.securityquestionapi.model.PreDefinedQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PreDefinedQuestionService {
    PreDefinedQuestion getById(int id) throws ResourceNotFoundException;
    Page<PreDefinedQuestion> getAll(Pageable pageable);
    PreDefinedQuestion save(String question);

    List<PreDefinedQuestion> saveAll(List<String> questions);
}
