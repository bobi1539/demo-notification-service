package com.zero.demonotificationservice.dto.response;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponseDto {
    private UUID id;
    private String name;
}
