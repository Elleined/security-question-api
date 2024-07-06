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
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public UserDTO save(@RequestParam("name") String name,
                        @RequestParam(defaultValue = "false", name = "includeRelatedLinks") boolean includeRelatedLinks) {

        User savedUser = userService.save(name);
        return userMapper.toDTO(savedUser)
                .addLinks(savedUser, includeRelatedLinks);
    }
}
