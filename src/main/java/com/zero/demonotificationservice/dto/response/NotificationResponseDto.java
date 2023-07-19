package com.zero.demonotificationservice.dto.response;

import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NotificationResponseDto {
    private Integer id;
    private String title;
    private String detail;
    private Timestamp createdAt;
    private UUID userId;
}
