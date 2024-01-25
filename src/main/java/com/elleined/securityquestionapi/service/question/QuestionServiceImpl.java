package com.elleined.securityquestionapi.service.question;

import com.elleined.securityquestionapi.exception.question.QuestionAlreadyExistsException;
import com.elleined.securityquestionapi.exception.resource.ResourceNotFoundException;
import com.elleined.securityquestionapi.mapper.QuestionMapper;
import com.elleined.securityquestionapi.model.question.Question;
import com.elleined.securityquestionapi.repository.question.QuestionRepository;
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
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    @Override
    public Question save(String question) {
        if (alreadyExists(question))
            throw new QuestionAlreadyExistsException("Cannot save question! becuase question already exists!");

        Question createdQuestion = questionMapper.toEntity(question);
        questionRepository.save(createdQuestion);
        log.debug("Question with id of {} saved successfully", createdQuestion.getId());
        return createdQuestion;
    }

    @Override
    public List<Question> saveAll(List<String> questions) {
        if (questions.stream().anyMatch(this::alreadyExists))
            throw new QuestionAlreadyExistsException("Cannot save all question! because one of the question already exists in database!");

        List<Question> questionList = questions.stream()
                .map(questionMapper::toEntity)
                .toList();

        questionRepository.saveAll(questionList);
        log.debug("Questions with ids of {} saved successfully", questionList.stream().map(Question::getId).toList());
        return questionList;
    }

    @Override
    public Question getById(int id) {
        return questionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Question with id of " + id + " does not exists!"));
    }

    @Override
    public boolean existsById(int id) {
        return questionRepository.existsById(id);
    }

    @Override
    public boolean alreadyExists(String question) {
        return questionRepository.findAll().stream()
                .map(Question::getQuestion)
                .anyMatch(question::equalsIgnoreCase);
    }

    @Override
    public List<Question> getAll() {
        return questionRepository.findAll().stream()
                .sorted(Comparator.comparingInt(Question::getId))
                .toList();
    }
}
