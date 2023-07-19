package com.zero.demonotificationservice.dto.request;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NotificationRequestDto {
    private String title;
    private String detail;
    private UUID userId;
    private Integer categoryId;
}
