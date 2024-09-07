package com.elleined.securityquestionapi.service.user;

import com.elleined.securityquestionapi.exception.resource.ResourceNotFoundException;
import com.elleined.securityquestionapi.jwt.JWTService;
import com.elleined.securityquestionapi.mapper.UserMapper;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id of " + id + " does not exists!"));
    }

    @Override
    public User getByJWT(String jwt) throws ResourceNotFoundException {
        String email = jwtService.getEmail(jwt);
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User with email of " + email + " doesn't exists!"));
    }

    @Override
    public User register(String name, String email, String password) {
        String hashedPassword = passwordEncoder.encode(password);
        User user = userMapper.toEntity(name, email, hashedPassword);
        userRepository.save(user);
        log.debug("User with id of {} saved successfully", user.getId());
        return user;
    }

    @Override
    public String login(String email,
                        String password) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        if (!authentication.isAuthenticated())
            throw new UsernameNotFoundException("Invalid credential");

        return jwtService.generateToken(email);
    }
}
