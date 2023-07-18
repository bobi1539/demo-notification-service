package com.zero.demonotificationservice.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BaseResponseDto<T> {
    private int code;
    private String message;
    private T data;
}
