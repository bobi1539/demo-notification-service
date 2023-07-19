package com.zero.demonotificationservice.controller;

import com.zero.demonotificationservice.dto.request.NotificationRequestDto;
import com.zero.demonotificationservice.dto.response.NotificationResponseDto;
import com.zero.demonotificationservice.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class NotificationController {

    private NotificationService notificationService;

    @PostMapping
    public NotificationResponseDto create(@RequestBody NotificationRequestDto requestDto) {
        long start = System.currentTimeMillis();
        NotificationResponseDto notificationResponseDto = notificationService.create(requestDto);
        long end = System.currentTimeMillis();
        log.info("duration : {}", end - start);
        return notificationResponseDto;
    }
}
