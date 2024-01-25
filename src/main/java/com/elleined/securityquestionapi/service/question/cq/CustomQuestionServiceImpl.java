package com.elleined.securityquestionapi.service.question.cq;

import com.elleined.securityquestionapi.exception.question.QuestionAlreadyExistsException;
import com.elleined.securityquestionapi.exception.resource.ResourceNotFoundException;
import com.elleined.securityquestionapi.mapper.question.CustomQuestionMapper;
import com.elleined.securityquestionapi.model.SecurityQuestion;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.question.CustomQuestion;
import com.elleined.securityquestionapi.model.question.Question;
import com.elleined.securityquestionapi.repository.question.CustomQuestionRepository;
import com.elleined.securityquestionapi.service.sq.SecurityQuestionService;
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
public class CustomQuestionServiceImpl implements CustomQuestionService {
    private final CustomQuestionRepository customQuestionRepository;
    private final CustomQuestionMapper customQuestionMapper;

    private final SecurityQuestionService securityQuestionService;

    @Override
    public CustomQuestion getById(int id) {
        return customQuestionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pre defined question with id of " + id + " doesn't exists!"));
    }

    @Override
    public boolean existsById(int id) {
        return customQuestionRepository.existsById(id);
    }

    @Override
    public boolean alreadyExists(String question) {
        return customQuestionRepository.findAll().stream()
                .map(Question::getQuestion)
                .anyMatch(question::equalsIgnoreCase);
    }

    @Override
    public CustomQuestion save(User currentUser, String question, String answer) {
        if (alreadyExists(question))
            throw new QuestionAlreadyExistsException("Cannot save question! becuase question already exists!");

        CustomQuestion createdQuestion = customQuestionMapper.toEntity(currentUser, question, answer);
        customQuestionRepository.save(createdQuestion);

        log.debug("Question with id of {} saved successfully", createdQuestion.getId());
        return createdQuestion;
    }

    @Override
    public List<CustomQuestion> getAllByOwner(User currentUser) {
        return currentUser.getCustomQuestions().stream()
                .sorted(Comparator.comparingInt(Question::getId))
                .toList();
    }
}
