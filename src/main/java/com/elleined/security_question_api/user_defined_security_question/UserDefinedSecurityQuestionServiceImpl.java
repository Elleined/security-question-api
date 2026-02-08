package com.elleined.security_question_api.user_defined_security_question;

import com.elleined.security_question_api.exception.SecurityQuestionAPIException;
import com.elleined.security_question_api.paging.PageRequest;
import jakarta.validation.constraints.NotNull;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Service
@Validated
@Transactional
public class UserDefinedSecurityQuestionServiceImpl implements UserDefinedSecurityQuestionService {
    private final UserDefinedSecurityQuestionRepository userDefinedSecurityQuestionRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDefinedSecurityQuestionServiceImpl(UserDefinedSecurityQuestionRepository userDefinedSecurityQuestionRepository, PasswordEncoder passwordEncoder) {
        this.userDefinedSecurityQuestionRepository = userDefinedSecurityQuestionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Cacheable(value = "userDefinedSecurityQuestion:getAll", key = "#resourceId + '-' + #request.page + '-' + #request.size")
    public List<UserDefinedSecurityQuestionDTO> getAll(UUID resourceId, PageRequest request) {
        return userDefinedSecurityQuestionRepository.findAll(resourceId, request.page(), request.size());
    }

    @Override
    @Cacheable(value = "userDefinedSecurityQuestion:getAllTotal", key = "#resourceId")
    public int getAllTotal(UUID resourceId) {
        return userDefinedSecurityQuestionRepository.findAllTotal(resourceId);
    }

    @Override
    @CacheEvict(value = {"userDefinedSecurityQuestion:getAll", "userDefinedSecurityQuestion:getAllTotal", "userDefinedSecurityQuestion:isAnswerCorrect"}, allEntries = true)
    public void save(@NotNull UserDefinedSecurityQuestionSaveRequest request) throws SecurityQuestionAPIException {
        String hashedAnswer = passwordEncoder.encode(request.answer());
        if (hashedAnswer == null)
            throw new SecurityQuestionAPIException("Something went wrong while hashing the answer");

        userDefinedSecurityQuestionRepository.save(request.resourceId(), request.question(), hashedAnswer);
    }

    @Override
    @CacheEvict(value = {"userDefinedSecurityQuestion:getAll", "userDefinedSecurityQuestion:getAllTotal", "userDefinedSecurityQuestion:isAnswerCorrect"}, allEntries = true)
    public void updateAnswer(UserDefinedSecurityQuestionRequest request) throws SecurityQuestionAPIException {
        String hashedAnswer = passwordEncoder.encode(request.answer());
        if (hashedAnswer == null)
            throw new SecurityQuestionAPIException("Something went wrong while hashing the answer");

        userDefinedSecurityQuestionRepository.updateAnswer(request.id(), request.resourceId(), hashedAnswer);
    }

    @Override
    @Cacheable(value = "userDefinedSecurityQuestion:isAnswerCorrect", key = "#request")
    public boolean isAnswerCorrect(UserDefinedSecurityQuestionRequest request) {
        String fetchedAnswer = userDefinedSecurityQuestionRepository.findAnswer(request.id(), request.resourceId());
        return passwordEncoder.matches(request.answer(), fetchedAnswer);
    }
}
