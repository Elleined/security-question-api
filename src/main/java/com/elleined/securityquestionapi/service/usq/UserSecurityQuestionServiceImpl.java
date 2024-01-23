package com.elleined.securityquestionapi.service.usq;

import com.elleined.securityquestionapi.exception.resource.ResourceNotFoundException;
import com.elleined.securityquestionapi.exception.resource.ResourceNotOwnedException;
import com.elleined.securityquestionapi.mapper.UserSecurityQuestionMapper;
import com.elleined.securityquestionapi.model.Question;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.UserSecurityQuestion;
import com.elleined.securityquestionapi.repository.UserSecurityQuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserSecurityQuestionServiceImpl implements UserSecurityQuestionService {
    private final UserSecurityQuestionRepository userSecurityQuestionRepository;
    private final UserSecurityQuestionMapper userSecurityQuestionMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserSecurityQuestion> getAllByUser(User currentUser) {
        return currentUser.getSecurityQuestions();
    }

    @Override
    public boolean isAnswerCorrect(User currentUser, UserSecurityQuestion userSecurityQuestion, String providedAnswer) {
        if (!currentUser.has(userSecurityQuestion))
            throw new ResourceNotOwnedException("Cannot check if answer correct! because current user doesn't own the security question provided!");

        String encodedPassword = userSecurityQuestion.getAnswer();
        return passwordEncoder.matches(providedAnswer, encodedPassword);
    }

    @Override
    public UserSecurityQuestion save(User currentUser, Question question, String answer) {
        String encodedAnswer = passwordEncoder.encode(answer);

        UserSecurityQuestion userSecurityQuestion = userSecurityQuestionMapper.toEntity(currentUser, question, encodedAnswer);
        userSecurityQuestionRepository.save(userSecurityQuestion);
        log.debug("User security question with id of {} saved successfully", userSecurityQuestion.getId());
        return userSecurityQuestion;
    }

    @Override
    public UserSecurityQuestion getById(int id) throws ResourceNotFoundException {
        return userSecurityQuestionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User security question with id of " + id + " doen't exists"));
    }
}
