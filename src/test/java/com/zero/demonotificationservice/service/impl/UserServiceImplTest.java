package com.zero.demonotificationservice.service.impl;

import com.zero.demonotificationservice.dto.request.UserRequestDto;
import com.zero.demonotificationservice.dto.response.UserResponseDto;
import com.zero.demonotificationservice.entity.MUser;
import com.zero.demonotificationservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testCreateSuccess() {
        UserRequestDto requestDto = new UserRequestDto();
        requestDto.setName("Ucup");

        MUser user = MUser.builder()
                .id(UUID.randomUUID())
                .name(requestDto.getName())
                .build();

        when(userRepository.save(any())).thenReturn(user);

        UserResponseDto userResponseDto = userService.create(requestDto);
        assertNotNull(userResponseDto);
        assertNotNull(userResponseDto.getId());
        assertNotNull(userResponseDto.getName());
        assertEquals("Ucup", userResponseDto.getName());

        verify(userRepository, times(1)).save(any());
    }
}