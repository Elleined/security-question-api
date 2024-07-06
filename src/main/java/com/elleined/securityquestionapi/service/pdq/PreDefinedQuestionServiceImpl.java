package com.elleined.securityquestionapi.service.pdq;

import com.elleined.securityquestionapi.exception.resource.ResourceNotFoundException;
import com.elleined.securityquestionapi.mapper.PreDefinedQuestionMapper;
import com.elleined.securityquestionapi.model.PreDefinedQuestion;
import com.elleined.securityquestionapi.repository.PreDefinedQuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PreDefinedQuestionServiceImpl implements PreDefinedQuestionService {
    private final PreDefinedQuestionRepository preDefinedQuestionRepository;
    private final PreDefinedQuestionMapper preDefinedQuestionMapper;

    @Override
    public PreDefinedQuestion getById(int id) {
        return preDefinedQuestionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pre defined question with id of " + id + " doesn't exists!"));
    }

    @Override
    public Page<PreDefinedQuestion> getAll(Pageable pageable) {
        return preDefinedQuestionRepository.findAll(pageable);
    }

    @Override
    public PreDefinedQuestion save(String question) {
        PreDefinedQuestion createdQuestion = preDefinedQuestionMapper.toEntity(question);

        preDefinedQuestionRepository.save(createdQuestion);
        log.debug("Pre defined question with id of {} saved successfully", createdQuestion.getId());
        return createdQuestion;
    }

    @Override
    public List<PreDefinedQuestion> saveAll(List<String> questions) {
        List<PreDefinedQuestion> predefinedQuestions = questions.stream()
                .map(preDefinedQuestionMapper::toEntity)
                .toList();

        preDefinedQuestionRepository.saveAll(predefinedQuestions);
        log.debug("Pre defined question saved successfully");
        return predefinedQuestions;
    }
}
