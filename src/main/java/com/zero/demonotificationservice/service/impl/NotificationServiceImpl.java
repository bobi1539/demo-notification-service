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
import jdk.dynalink.linker.GuardedInvocationTransformer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

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

    @Override
    public List<NotificationResponseDto> getNotifications(String userId) {
        UUID uuidUserId = null;
        try {
            uuidUserId = UUID.fromString(userId);
        } catch (Exception e) {
            throw new BusinessException(GlobalMessage.UUID_NOT_VALID);
        }
        MUser user = findById(uuidUserId);
        List<MNotification> notifications = notificationRepository.findAll(
                searchByUser(user), Sort.by("createdAt").descending()
        );
        List<NotificationResponseDto> notificationResponseDtos = new ArrayList<>();
        notifications.forEach(notification -> {
            NotificationResponseDto notificationResponseDto = NotificationResponseDto.builder()
                    .id(notification.getId())
                    .title(notification.getTitle())
                    .detail(notification.getDetail())
                    .createdAt(notification.getCreatedAt())
                    .userId(getUserIdFromNotification(notification))
                    .build();
            notificationResponseDtos.add(notificationResponseDto);
        });
        return notificationResponseDtos;
    }

    private MUser findById(UUID userId) {
        if (Objects.isNull(userId)) {
            return null;
        }
        return userRepository.findById(userId).orElseThrow(
                () -> new BusinessException(GlobalMessage.NOT_FOUND)
        );
    }

    private UUID getUserIdFromNotification(MNotification notification) {
        if (Objects.isNull(notification.getUser())) {
            return null;
        }
        return notification.getUser().getId();
    }

    private Specification<MNotification> searchByUser(MUser user) {
        Specification<MNotification> specification = (root, query, criteriaBuilder) -> {
            if (user == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("user"), user);
        };
        return specification.or(((root, query, criteriaBuilder) -> {
            return criteriaBuilder.isNull(root.get("user"));
        }));
    }
}
