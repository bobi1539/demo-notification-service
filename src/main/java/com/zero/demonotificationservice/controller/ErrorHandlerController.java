package com.zero.demonotificationservice.controller;

import com.zero.demonotificationservice.dto.response.BaseResponseDto;
import com.zero.demonotificationservice.util.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ErrorHandlerController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponseDto> exception(Exception e) {
        log.error("Error : ", e);
        BaseResponseDto<Object> baseResponseDto = BaseResponseDto.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .build();
        return new ResponseEntity<>(baseResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<BaseResponseDto> exception(RuntimeException e) {
        log.error("Error : ", e);
        BaseResponseDto<Object> baseResponseDto = BaseResponseDto.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .build();
        return new ResponseEntity<>(baseResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BaseResponseDto> exception(BusinessException e) {
        log.error("Error : ", e);
        BaseResponseDto<Object> baseResponseDto = BaseResponseDto.builder()
                .code(e.getCode().value())
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(baseResponseDto, e.getCode());
    }
}
