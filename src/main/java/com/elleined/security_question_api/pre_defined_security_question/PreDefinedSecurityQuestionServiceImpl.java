package com.elleined.security_question_api.pre_defined_security_question;

import com.elleined.security_question_api.exception.SecurityQuestionAPIException;
import com.elleined.security_question_api.paging.PageRequest;
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
public class PreDefinedSecurityQuestionServiceImpl implements PreDefinedSecurityQuestionService {
    private final PreDefinedSecurityQuestionRepository preDefinedSecurityQuestionRepository;
    private final PasswordEncoder passwordEncoder;

    public PreDefinedSecurityQuestionServiceImpl(PreDefinedSecurityQuestionRepository preDefinedSecurityQuestionRepository, PasswordEncoder passwordEncoder) {
        this.preDefinedSecurityQuestionRepository = preDefinedSecurityQuestionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Cacheable(value = "preDefinedSecurityQuestion:getAll", key = "#resourceId + '-' + #request.page + '-' + #request.size")
    public List<PreDefinedSecurityQuestionDTO> getAll(UUID resourceId, PageRequest request) {
        return preDefinedSecurityQuestionRepository.findAll(resourceId, request.page(), request.size());
    }

    @Override
    @Cacheable(value = "preDefinedSecurityQuestion:getAllTotal", key = "#resourceId")
    public int getAllTotal(UUID resourceId) {
        return preDefinedSecurityQuestionRepository.findAllTotal(resourceId);
    }

    @Override
    @CacheEvict(value = {"preDefinedSecurityQuestion:getAll", "preDefinedSecurityQuestion:getAllTotal", "preDefinedSecurityQuestion:isAnswerCorrect"}, allEntries = true)
    public void save(PreDefinedSecurityQuestionRequest request) throws SecurityQuestionAPIException {
        if (preDefinedSecurityQuestionRepository.isExists(request.resourceId(), request.securityQuestionId()))
            throw new SecurityQuestionAPIException("resource id and security question id combination already exists");

        String hashedAnswer = passwordEncoder.encode(request.answer());
        if (hashedAnswer == null)
            throw new SecurityQuestionAPIException("Something went wrong while hashing the answer");

        preDefinedSecurityQuestionRepository.save(request.resourceId(), request.securityQuestionId(), hashedAnswer);
    }

    @Override
    @CacheEvict(value = {"preDefinedSecurityQuestion:getAll", "preDefinedSecurityQuestion:getAllTotal", "preDefinedSecurityQuestion:isAnswerCorrect"}, allEntries = true)
    public void updateAnswer(PreDefinedSecurityQuestionRequest request) throws SecurityQuestionAPIException {
        String hashedAnswer = passwordEncoder.encode(request.answer());
        if (hashedAnswer == null)
            throw new SecurityQuestionAPIException("Something went wrong while hashing the answer");

        preDefinedSecurityQuestionRepository.updateAnswer(request.resourceId(), request.securityQuestionId(), hashedAnswer);
    }

    @Override
    @Cacheable(value = "preDefinedSecurityQuestion:isAnswerCorrect", key = "#request")
    public boolean isAnswerCorrect(PreDefinedSecurityQuestionRequest request) {
        String fetchedAnswer = preDefinedSecurityQuestionRepository.findAnswer(request.resourceId(), request.securityQuestionId());
        return passwordEncoder.matches(request.answer(), fetchedAnswer);
    }
}
