package com.elleined.securityquestionapi.service.question.pdq;

import com.elleined.securityquestionapi.exception.question.QuestionAlreadyExistsException;
import com.elleined.securityquestionapi.exception.resource.ResourceNotFoundException;
import com.elleined.securityquestionapi.mapper.QuestionMapper;
import com.elleined.securityquestionapi.model.question.PreDefinedQuestion;
import com.elleined.securityquestionapi.model.question.Question;
import com.elleined.securityquestionapi.repository.question.PreDefinedQuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PreDefinedQuestionServiceImpl implements PreDefinedQuestionService {
    private final PreDefinedQuestionRepository preDefinedQuestionRepository;
    private final QuestionMapper questionMapper;

    @Override
    public PreDefinedQuestion getById(int id) {
        return preDefinedQuestionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pre defined question with id of " + id + " doesn't exists!"));
    }

    @Override
    public boolean existsById(int id) {
        return preDefinedQuestionRepository.existsById(id);
    }

    @Override
    public boolean alreadyExists(String question) {
        return preDefinedQuestionRepository.findAll().stream()
                .map(Question::getQuestion)
                .anyMatch(question::equalsIgnoreCase);
    }

    @Override
    public List<PreDefinedQuestion> getAll() {
        return preDefinedQuestionRepository.findAll().stream()
                .sorted(Comparator.comparingInt(Question::getId))
                .toList();
    }

    @Override
    public PreDefinedQuestion save(String question) {
        if (alreadyExists(question))
            throw new QuestionAlreadyExistsException("Cannot save question! becuase question already exists!");

        PreDefinedQuestion createdQuestion = questionMapper.toEntity(question);
        preDefinedQuestionRepository.save(createdQuestion);
        log.debug("Question with id of {} saved successfully", createdQuestion.getId());
        return createdQuestion;
    }

    @Override
    public List<PreDefinedQuestion> saveAll(List<String> questions) {
        if (questions.stream().anyMatch(this::alreadyExists))
            throw new QuestionAlreadyExistsException("Cannot save all question! because one of the question already exists in database!");

        List<PreDefinedQuestion> questionList = questions.stream()
                .map(questionMapper::toEntity)
                .toList();

        preDefinedQuestionRepository.saveAll(questionList);
        log.debug("Questions with ids of {} saved successfully", questionList.stream().map(Question::getId).toList());
        return questionList;
    }
}
