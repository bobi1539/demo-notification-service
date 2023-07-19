package com.zero.demonotificationservice.controller;

import com.zero.demonotificationservice.dto.request.UserRequestDto;
import com.zero.demonotificationservice.dto.response.UserResponseDto;
import com.zero.demonotificationservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserResponseDto create(@RequestBody UserRequestDto requestDto) {
        return userService.create(requestDto);
    }

    @GetMapping
    public List<UserResponseDto> getUsers() {
        return userService.getUsers();
    }
}
