package com.elleined.securityquestionapi.service.question.cq;

import com.elleined.securityquestionapi.exception.question.QuestionAlreadyExistsException;
import com.elleined.securityquestionapi.exception.resource.ResourceNotFoundException;
import com.elleined.securityquestionapi.exception.resource.ResourceNotOwnedException;
import com.elleined.securityquestionapi.mapper.question.CustomQuestionMapper;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.sq.SecurityQuestion;
import com.elleined.securityquestionapi.model.sq.UserDefinedSecurityQuestion;
import com.elleined.securityquestionapi.repository.sq.UserDefinedSecurityQuestionRepository;
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
    private final UserDefinedSecurityQuestionRepository userDefinedSecurityQuestionRepository;
    private final CustomQuestionMapper customQuestionMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDefinedSecurityQuestion getById(int id) {
        return userDefinedSecurityQuestionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pre defined question with id of " + id + " doesn't exists!"));
    }

    @Override
    public boolean existsById(int id) {
        return userDefinedSecurityQuestionRepository.existsById(id);
    }

    @Override
    public boolean alreadyExists(String question) {
        return userDefinedSecurityQuestionRepository.findAll().stream()
                .map(SecurityQuestion::getQuestion)
                .anyMatch(question::equalsIgnoreCase);
    }

    @Override
    public UserDefinedSecurityQuestion save(User currentUser, String question, String answer) {
        if (alreadyExists(question))
            throw new QuestionAlreadyExistsException("Cannot save question! becuase question already exists!");

        String encodedPassword = passwordEncoder.encode(answer);
        UserDefinedSecurityQuestion createdQuestion = customQuestionMapper.toEntity(currentUser, question, encodedPassword);
        userDefinedSecurityQuestionRepository.save(createdQuestion);

        log.debug("Question with id of {} saved successfully", createdQuestion.getId());
        return createdQuestion;
    }

    @Override
    public boolean isAnswerCorrect(User currentUser, UserDefinedSecurityQuestion userDefinedQuestion, String providedAnswer) {
        if (!currentUser.has(userDefinedQuestion))
            throw new ResourceNotOwnedException("Cannot check if answer correct! because current user doesn't own the security question provided!");

        String encodedAnswer = userDefinedQuestion.getAnswer();
        return passwordEncoder.matches(providedAnswer, encodedAnswer);
    }

    @Override
    public List<UserDefinedSecurityQuestion> getAllByOwner(User currentUser) {
        return currentUser.getUserDefinedQuestions().stream()
                .sorted(Comparator.comparingInt(SecurityQuestion::getId))
                .toList();
    }
}
