package com.zero.demonotificationservice.service.impl;

import com.zero.demonotificationservice.constant.GlobalMessage;
import com.zero.demonotificationservice.dto.request.UserRequestDto;
import com.zero.demonotificationservice.dto.response.UserResponseDto;
import com.zero.demonotificationservice.entity.MUser;
import com.zero.demonotificationservice.repository.UserRepository;
import com.zero.demonotificationservice.service.UserService;
import com.zero.demonotificationservice.util.BusinessException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public UserResponseDto create(UserRequestDto requestDto) {
        log.info("create user");
        MUser user = MUser.builder()
                .id(UUID.randomUUID())
                .name(requestDto.getName())
                .build();
        MUser userSaved = userRepository.save(user);
        return UserResponseDto.builder()
                .id(userSaved.getId())
                .name(userSaved.getName())
                .build();
    }
}
