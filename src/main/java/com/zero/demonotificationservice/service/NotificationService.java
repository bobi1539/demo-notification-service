package com.zero.demonotificationservice.service;

import com.zero.demonotificationservice.dto.request.NotificationRequestDto;
import com.zero.demonotificationservice.dto.response.NotificationResponseDto;

public interface NotificationService {
    NotificationResponseDto create(NotificationRequestDto requestDto);
}
