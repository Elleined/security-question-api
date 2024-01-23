package com.elleined.securityquestionapi.service.user;

import com.elleined.securityquestionapi.exception.resource.ResourceNotFoundException;
import com.elleined.securityquestionapi.mapper.UserMapper;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User save(String name) {
        User user = userMapper.toEntity(name);
        userRepository.save(user);
        log.debug("User with id of {} saved successfully", user.getId());
        return user;
    }

    @Override
    public User getById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id of " + id + " does not exists!"));
    }
}
