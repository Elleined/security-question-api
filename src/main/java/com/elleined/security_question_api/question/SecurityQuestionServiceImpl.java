package com.elleined.security_question_api.question;

import com.elleined.security_question_api.paging.PageRequest;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Service
@Validated
@Transactional
public class SecurityQuestionServiceImpl implements SecurityQuestionService {
    private final SecurityQuestionRepository securityQuestionRepository;

    public SecurityQuestionServiceImpl(SecurityQuestionRepository securityQuestionRepository) {
        this.securityQuestionRepository = securityQuestionRepository;
    }

    @Override
    @Cacheable(value = "securityQuestion:getAll", key = "#name + '-' + #request.page + '-' + #request.size")
    public List<SecurityQuestionDTO> getAll(String name, PageRequest request) {
        return securityQuestionRepository.findAll(name, request.page(), request.size());
    }

    @Override
    @Cacheable(value = "securityQuestion:getAllTotal", key = "#name")
    public int getAllTotal(String name) {
        return securityQuestionRepository.findAllTotal(name);
    }

    @Override
    @CacheEvict(value = {"securityQuestion:getAll", "securityQuestion:getAllTotal"}, allEntries = true)
    public void save(String name) {
        securityQuestionRepository.save(name);
    }

    @Override
    @CacheEvict(value = {"securityQuestion:getAll", "securityQuestion:getAllTotal"}, allEntries = true)
    public void update(UUID id, String name) {
        securityQuestionRepository.update(id, name);
    }

    @Override
    @CacheEvict(value = {"securityQuestion:getAll", "securityQuestion:getAllTotal"}, allEntries = true)
    public void delete(UUID id) {
        securityQuestionRepository.delete(id);
    }
}
