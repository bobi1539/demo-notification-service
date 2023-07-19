package com.zero.demonotificationservice.service.impl;

import com.zero.demonotificationservice.constant.GlobalMessage;
import com.zero.demonotificationservice.dto.request.NotificationRequestDto;
import com.zero.demonotificationservice.dto.response.CategoryResponseDto;
import com.zero.demonotificationservice.dto.response.NotificationResponseDto;
import com.zero.demonotificationservice.entity.MCategory;
import com.zero.demonotificationservice.entity.MNotification;
import com.zero.demonotificationservice.entity.MUser;
import com.zero.demonotificationservice.repository.CategoryRepository;
import com.zero.demonotificationservice.repository.NotificationRepository;
import com.zero.demonotificationservice.repository.UserRepository;
import com.zero.demonotificationservice.service.NotificationService;
import com.zero.demonotificationservice.util.BusinessException;
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
    private final CategoryRepository categoryRepository;

    @Override
    public NotificationResponseDto create(NotificationRequestDto requestDto) {
        MUser user = findUserBy(requestDto.getUserId());
        MCategory category = findCategoryBy(requestDto.getCategoryId());

        MNotification notification = MNotification.builder()
                .title(requestDto.getTitle())
                .detail(requestDto.getDetail())
                .user(user)
                .category(category)
                .build();
        MNotification notificationSaved = notificationRepository.save(notification);

        CategoryResponseDto categoryResponseDto = CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();

        return NotificationResponseDto.builder()
                .id(notificationSaved.getId())
                .title(notificationSaved.getTitle())
                .detail(notificationSaved.getDetail())
                .createdAt(notificationSaved.getCreatedAt())
                .userId(requestDto.getUserId())
                .category(categoryResponseDto)
                .build();
    }

    @Override
    public List<NotificationResponseDto> getNotifications(String userId, Integer categoryId) {
        UUID uuidUserId = null;
        try {
            uuidUserId = UUID.fromString(userId);
        } catch (Exception e) {
            throw new BusinessException(GlobalMessage.UUID_NOT_VALID);
        }
        MUser user = findUserBy(uuidUserId);
        List<MNotification> notifications = notificationRepository.findAll(
                searchBy(user, categoryId), Sort.by("createdAt").descending()
        );
        List<NotificationResponseDto> notificationResponseDtos = new ArrayList<>();
        notifications.forEach(notification -> {
            CategoryResponseDto categoryResponseDto;
            if (Objects.isNull(notification.getCategory())) {
                categoryResponseDto = null;
            } else {
                categoryResponseDto = CategoryResponseDto.builder()
                        .id(notification.getCategory().getId())
                        .name(notification.getCategory().getName())
                        .build();
            }

            NotificationResponseDto notificationResponseDto = NotificationResponseDto.builder()
                    .id(notification.getId())
                    .title(notification.getTitle())
                    .detail(notification.getDetail())
                    .createdAt(notification.getCreatedAt())
                    .userId(getUserIdFromNotification(notification))
                    .category(categoryResponseDto)
                    .build();
            notificationResponseDtos.add(notificationResponseDto);
        });
        return notificationResponseDtos;
    }

    private MUser findUserBy(UUID userId) {
        if (Objects.isNull(userId)) {
            return null;
        }
        return userRepository.findById(userId).orElseThrow(
                () -> new BusinessException(GlobalMessage.NOT_FOUND)
        );
    }

    private MCategory findCategoryBy(Integer categoryId) {
        if (Objects.isNull(categoryId)) {
            throw new BusinessException(GlobalMessage.NOT_FOUND);
        }
        return categoryRepository.findById(categoryId).orElseThrow(() -> new BusinessException(GlobalMessage.NOT_FOUND));
    }

    private UUID getUserIdFromNotification(MNotification notification) {
        if (Objects.isNull(notification.getUser())) {
            return null;
        }
        return notification.getUser().getId();
    }

    private Specification<MNotification> searchBy(MUser user, Integer categoryId) {
        Specification<MNotification> specification = (root, query, criteriaBuilder) -> {
            if (user == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("user"), user);
        };
        Specification<MNotification> specification1 = specification.or(((root, query, criteriaBuilder) -> {
            return criteriaBuilder.isNull(root.get("user"));
        }));
        if (Objects.nonNull(categoryId)) {
            MCategory category = categoryRepository.findById(categoryId).orElseThrow(
                    () -> new BusinessException(GlobalMessage.NOT_FOUND)
            );
            return specification1.and(((root, query, criteriaBuilder) -> {
                return criteriaBuilder.equal(root.get("category"), category);
            }));
        }
        return specification1;
    }
}
