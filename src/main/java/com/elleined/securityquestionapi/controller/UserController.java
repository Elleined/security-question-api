package com.elleined.securityquestionapi.controller;

import com.elleined.securityquestionapi.dto.UserDTO;
import com.elleined.securityquestionapi.mapper.UserMapper;
import com.elleined.securityquestionapi.model.User;
import com.elleined.securityquestionapi.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public UserDTO save(@RequestParam("name") String name) {
        User savedUser = userService.save(name);
        return userMapper.toDTO(savedUser);
    }

    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable("id") int id) {
        User retrievedUser = userService.getById(id);
        return userMapper.toDTO(retrievedUser).addLinks();
    }

}
