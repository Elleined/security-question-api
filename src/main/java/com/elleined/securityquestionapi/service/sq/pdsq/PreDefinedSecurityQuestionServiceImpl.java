package com.elleined.securityquestionapi.service.sq.pdsq;

import com.elleined.securityquestionapi.exception.resource.ResourceNotFoundException;
import com.elleined.securityquestionapi.exception.resource.ResourceNotOwnedException;
import com.elleined.securityquestionapi.mapper.sq.PreDefinedSecurityQuestionMapper;
import com.elleined.securityquestionapi.model.PreDefinedQuestion;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.model.sq.PreDefinedSecurityQuestion;
import com.elleined.securityquestionapi.repository.sq.PreDefinedSecurityQuestionRepository;
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
public class PreDefinedSecurityQuestionServiceImpl implements PreDefinedSecurityQuestionService {
    private final PreDefinedSecurityQuestionRepository preDefinedSecurityQuestionRepository;
    private final PreDefinedSecurityQuestionMapper preDefinedSecurityQuestionMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public PreDefinedSecurityQuestion save(User currentUser, PreDefinedQuestion preDefinedQuestion, String answer) {
        PreDefinedSecurityQuestion preDefinedSecurityQuestion = preDefinedSecurityQuestionMapper.toEntity(currentUser, preDefinedQuestion, answer);

        preDefinedSecurityQuestionRepository.save(preDefinedSecurityQuestion);
        log.debug("Saving pre defined security question success.");
        return preDefinedSecurityQuestion;
    }

    @Override
    public PreDefinedSecurityQuestion getById(int id) throws ResourceNotFoundException {
        return preDefinedSecurityQuestionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Security question doesn't exists!"));
    }

    @Override
    public Page<PreDefinedSecurityQuestion> getAll(User currentUser, Pageable pageable) {
        return preDefinedSecurityQuestionRepository.findAll(currentUser, pageable);
    }

    @Override
    public boolean isAnswerCorrect(User currentUser, PreDefinedSecurityQuestion securityQuestion, String providedAnswer) {
        if (!currentUser.has(securityQuestion))
            throw new ResourceNotOwnedException("Cannot check if answer correct! because current user doesn't own the security question provided!");

        return passwordEncoder.matches(providedAnswer, securityQuestion.getAnswer());
    }
}
