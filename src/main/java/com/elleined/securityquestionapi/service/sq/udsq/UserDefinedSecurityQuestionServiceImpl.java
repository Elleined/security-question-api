package com.elleined.securityquestionapi.service.sq.udsq;

import com.elleined.securityquestionapi.exception.resource.ResourceNotFoundException;
import com.elleined.securityquestionapi.exception.resource.ResourceNotOwnedException;
import com.elleined.securityquestionapi.mapper.sq.UserDefinedSecurityQuestionMapper;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.sq.UserDefinedSecurityQuestion;
import com.elleined.securityquestionapi.repository.sq.UserDefinedSecurityQuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserDefinedSecurityQuestionServiceImpl implements UserDefinedSecurityQuestionService {
    private final UserDefinedSecurityQuestionRepository userDefinedSecurityQuestionRepository;
    private final UserDefinedSecurityQuestionMapper userDefinedSecurityQuestionMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDefinedSecurityQuestion save(User currentUser, String question, String answer) {
        String encodedPassword = passwordEncoder.encode(answer);
        UserDefinedSecurityQuestion userDefinedSecurityQuestion = userDefinedSecurityQuestionMapper.toEntity(currentUser, question, encodedPassword);

        userDefinedSecurityQuestionRepository.save(userDefinedSecurityQuestion);
        log.debug("Saving user defined question success.");
        return userDefinedSecurityQuestion;
    }

    @Override
    public UserDefinedSecurityQuestion getById(int id) throws ResourceNotFoundException {
        return userDefinedSecurityQuestionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Security question doesn't exists!"));
    }

    @Override
    public Page<UserDefinedSecurityQuestion> getAll(User currentUser, Pageable pageable) {
        return userDefinedSecurityQuestionRepository.findAll(currentUser, pageable);
    }

    @Override
    public boolean isAnswerCorrect(User currentUser, UserDefinedSecurityQuestion securityQuestion, String providedAnswer) {
        if (!currentUser.has(securityQuestion))
            throw new ResourceNotOwnedException("Cannot check if answer correct! because current user doesn't own the security question provided!");

        return passwordEncoder.matches(providedAnswer, securityQuestion.getAnswer());
    }
}
