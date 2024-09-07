package com.elleined.securityquestionapi.controller;

import com.elleined.securityquestionapi.dto.UserDTO;
import com.elleined.securityquestionapi.mapper.UserMapper;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public UserDTO save(@RequestParam("name") String name,
                        @RequestParam("email") String email,
                        @RequestParam("password") String password) {

        User user = userService.register(name, email, password);
        return userMapper.toDTO(user);
    }
}
