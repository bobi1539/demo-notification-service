package com.zero.demonotificationservice.service;

import com.zero.demonotificationservice.dto.request.NotificationRequestDto;
import com.zero.demonotificationservice.dto.response.NotificationResponseDto;

import java.util.List;
import java.util.UUID;

public interface NotificationService {
    NotificationResponseDto create(NotificationRequestDto requestDto);
    List<NotificationResponseDto> getNotifications(String userId);
}
