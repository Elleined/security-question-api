package com.elleined.securityquestionapi.service.question.cq;

import com.elleined.securityquestionapi.exception.question.QuestionAlreadyExistsException;
import com.elleined.securityquestionapi.exception.resource.ResourceNotFoundException;
import com.elleined.securityquestionapi.exception.resource.ResourceNotOwnedException;
import com.elleined.securityquestionapi.mapper.question.CustomQuestionMapper;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.question.CustomQuestion;
import com.elleined.securityquestionapi.model.question.Question;
import com.elleined.securityquestionapi.repository.question.CustomQuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final PasswordEncoder passwordEncoder;

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

        String encodedPassword = passwordEncoder.encode(answer);
        CustomQuestion createdQuestion = customQuestionMapper.toEntity(currentUser, question, encodedPassword);
        customQuestionRepository.save(createdQuestion);

        log.debug("Question with id of {} saved successfully", createdQuestion.getId());
        return createdQuestion;
    }

    @Override
    public boolean isAnswerCorrect(User currentUser, CustomQuestion customQuestion, String providedAnswer) {
        if (!currentUser.has(customQuestion))
            throw new ResourceNotOwnedException("Cannot check if answer correct! because current user doesn't own the security question provided!");

        String encodedPassword = customQuestion.getAnswer();
        return passwordEncoder.matches(providedAnswer, encodedPassword);
    }

    @Override
    public List<CustomQuestion> getAllByOwner(User currentUser) {
        return currentUser.getCustomQuestions().stream()
                .sorted(Comparator.comparingInt(Question::getId))
                .toList();
    }
}
