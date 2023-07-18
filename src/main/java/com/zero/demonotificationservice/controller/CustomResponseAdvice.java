package com.zero.demonotificationservice.controller;

import com.zero.demonotificationservice.constant.GlobalMessage;
import com.zero.demonotificationservice.dto.response.BaseResponseDto;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;
import java.util.Map;

@RestControllerAdvice(basePackages = "com.zero.demonotificationservice.controller")
public final class CustomResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response
    ) {
        if (returnType.getContainingClass().isAnnotationPresent(RestController.class)) {
            return buildSuccessResponse(body);
        }
        return body;
    }

    private BaseResponseDto<Object> buildSuccessResponse(Object data) {
        return BaseResponseDto.builder()
                .code(GlobalMessage.SUCCESS.code.value())
                .message(GlobalMessage.SUCCESS.message)
                .data(data)
                .build();
    }
}
