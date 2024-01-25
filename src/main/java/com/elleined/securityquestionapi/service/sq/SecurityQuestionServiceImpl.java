package com.elleined.securityquestionapi.service.sq;

import com.elleined.securityquestionapi.exception.SecurityQuestionLimitException;
import com.elleined.securityquestionapi.exception.resource.ResourceNotFoundException;
import com.elleined.securityquestionapi.exception.resource.ResourceNotOwnedException;
import com.elleined.securityquestionapi.mapper.SecurityQuestionMapper;
import com.elleined.securityquestionapi.model.question.Question;
import com.elleined.securityquestionapi.model.SecurityQuestion;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.repository.SecurityQuestionRepository;
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
public class SecurityQuestionServiceImpl implements SecurityQuestionService {
    private final SecurityQuestionRepository securityQuestionRepository;
    private final SecurityQuestionMapper securityQuestionMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public List<SecurityQuestion> getAllByUser(User currentUser) {
        return currentUser.getSecurityQuestions().stream()
                .sorted(Comparator.comparingInt(SecurityQuestion::getId))
                .toList();
    }

    @Override
    public boolean isAnswerCorrect(User currentUser, SecurityQuestion securityQuestion, String providedAnswer) {
        if (!currentUser.has(securityQuestion))
            throw new ResourceNotOwnedException("Cannot check if answer correct! because current user doesn't own the security question provided!");

        String encodedPassword = securityQuestion.getAnswer();
        return passwordEncoder.matches(providedAnswer, encodedPassword);
    }

    @Override
    public SecurityQuestion save(User currentUser, Question question, String answer) {
        if (currentUser.hasReachedLimitOfSecurityQuestions()) throw new SecurityQuestionLimitException("Cannot save security question! because you already reached the limit which is only " + SECURITY_QUESTION_LIMIT + " per user!");
        String encodedAnswer = passwordEncoder.encode(answer);

        SecurityQuestion securityQuestion = securityQuestionMapper.toEntity(currentUser, question, encodedAnswer);
        securityQuestionRepository.save(securityQuestion);
        log.debug("User security question with id of {} saved successfully", securityQuestion.getId());
        return securityQuestion;
    }

    @Override
    public SecurityQuestion getById(int id) throws ResourceNotFoundException {
        return securityQuestionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User security question with id of " + id + " doen't exists"));
    }
}
