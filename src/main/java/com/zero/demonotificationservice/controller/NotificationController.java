package com.zero.demonotificationservice.controller;

import com.zero.demonotificationservice.dto.request.NotificationRequestDto;
import com.zero.demonotificationservice.dto.response.NotificationResponseDto;
import com.zero.demonotificationservice.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public NotificationResponseDto create(@RequestBody NotificationRequestDto requestDto) {
        long start = System.currentTimeMillis();
        NotificationResponseDto notificationResponseDto = notificationService.create(requestDto);
        long end = System.currentTimeMillis();
        log.info("duration : {} ms", end - start);
        return notificationResponseDto;
    }

    @GetMapping
    public List<NotificationResponseDto> getNotifications(
            @RequestParam String userId,
            @RequestParam(required = false) Integer categoryId
    ) {
        long start = System.currentTimeMillis();
        List<NotificationResponseDto> notificationResponseDtos = notificationService.getNotifications(userId, categoryId);
        long end = System.currentTimeMillis();
        log.info("duration : {} ms", end - start);
        return notificationResponseDtos;
    }
}
