package com.elleined.security_question_api.pre_defined_security_question;

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
public class PreDefinedSecurityQuestionServiceImpl implements PreDefinedSecurityQuestionService {
    private final PreDefinedSecurityQuestionRepository preDefinedSecurityQuestionRepository;
    private final PasswordEncoder passwordEncoder;

    public PreDefinedSecurityQuestionServiceImpl(PreDefinedSecurityQuestionRepository preDefinedSecurityQuestionRepository, PasswordEncoder passwordEncoder) {
        this.preDefinedSecurityQuestionRepository = preDefinedSecurityQuestionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Cacheable(value = "preDefinedSecurityQuestion:getAll", key = "#id + '-' + #request.page + '-' + #request.size")
    public List<PreDefinedSecurityQuestionDTO> getAll(UUID id, PageRequest request) {
        return preDefinedSecurityQuestionRepository.findAll(id, request.page(), request.size());
    }

    @Override
    @Cacheable(value = "preDefinedSecurityQuestion:getAllTotal", key = "#id")
    public int getAllTotal(UUID id) {
        return preDefinedSecurityQuestionRepository.findAllTotal(id);
    }

    @Override
    @CacheEvict(value = {"preDefinedSecurityQuestion:getAll", "preDefinedSecurityQuestion:getAllTotal"}, allEntries = true)
    public void save(PreDefinedSecurityQuestionRequest request) throws SecurityQuestionAPIException {
        if (preDefinedSecurityQuestionRepository.isExists(request.resourceId(), request.securityQuestionId()))
            throw new SecurityQuestionAPIException("resource id and security question id combination already exists");

        String hashedAnswer = passwordEncoder.encode(request.answer());
        if (hashedAnswer == null)
            throw new SecurityQuestionAPIException("Something went wrong while hashing the answer");

        preDefinedSecurityQuestionRepository.save(request.resourceId(), request.securityQuestionId(), hashedAnswer);
    }

    @Override
    @CacheEvict(value = {"preDefinedSecurityQuestion:getAll", "preDefinedSecurityQuestion:getAllTotal"}, allEntries = true)
    public void updateAnswer(@NotNull PreDefinedSecurityQuestionRequest request) throws SecurityQuestionAPIException {
        String hashedAnswer = passwordEncoder.encode(request.answer());
        if (hashedAnswer == null)
            throw new SecurityQuestionAPIException("Something went wrong while hashing the answer");

        preDefinedSecurityQuestionRepository.updateAnswer(request.resourceId(), request.securityQuestionId(), hashedAnswer);
    }

    @Override
    @Cacheable(value = "preDefinedSecurityQuestion:getAll", key = "#request")
    public boolean isAnswerCorrect(PreDefinedSecurityQuestionRequest request) {
        String fetchedAnswer = preDefinedSecurityQuestionRepository.findAnswer(request.resourceId(), request.securityQuestionId());
        String hashedAnswer = passwordEncoder.encode(request.answer());

        return passwordEncoder.matches(request.answer(), fetchedAnswer);
    }
}
