package com.zero.demonotificationservice.service.impl;

import com.zero.demonotificationservice.constant.GlobalMessage;
import com.zero.demonotificationservice.dto.request.NotificationRequestDto;
import com.zero.demonotificationservice.dto.response.NotificationResponseDto;
import com.zero.demonotificationservice.entity.MNotification;
import com.zero.demonotificationservice.entity.MUser;
import com.zero.demonotificationservice.repository.NotificationRepository;
import com.zero.demonotificationservice.repository.UserRepository;
import com.zero.demonotificationservice.service.NotificationService;
import com.zero.demonotificationservice.util.BusinessException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private NotificationRepository notificationRepository;
    private UserRepository userRepository;

    @Override
    public NotificationResponseDto create(NotificationRequestDto requestDto) {
        MUser user = findById(requestDto.getUserId());
        MNotification notification = MNotification.builder()
                .title(requestDto.getTitle())
                .detail(requestDto.getDetail())
                .user(user)
                .build();
        MNotification notificationSaved = notificationRepository.save(notification);
        return NotificationResponseDto.builder()
                .id(notificationSaved.getId())
                .title(notificationSaved.getTitle())
                .detail(notificationSaved.getDetail())
                .createdAt(notificationSaved.getCreatedAt())
                .userId(requestDto.getUserId())
                .build();
    }

    public MUser findById(UUID userId) {
        if (Objects.isNull(userId)) {
            return null;
        }
        return userRepository.findById(userId).orElseThrow(
                () -> new BusinessException(GlobalMessage.NOT_FOUND)
        );
    }
}
