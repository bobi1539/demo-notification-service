package com.zero.demonotificationservice.service;

import com.zero.demonotificationservice.dto.request.UserRequestDto;
import com.zero.demonotificationservice.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto create(UserRequestDto requestDto);
    List<UserResponseDto> getUsers();
}
