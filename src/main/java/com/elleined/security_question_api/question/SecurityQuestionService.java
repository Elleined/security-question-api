package com.elleined.security_question_api.question;

import com.elleined.security_question_api.paging.PageRequest;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface SecurityQuestionService {

    List<SecurityQuestionDTO> getAll(String name,
                                     @NotNull PageRequest request);

    int getAllTotal(String name);
}
