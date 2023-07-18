package com.zero.demonotificationservice.constant;

import org.springframework.http.HttpStatus;

public enum GlobalMessage {
    SUCCESS(HttpStatus.OK, "Success"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "Data Not Found");

    public HttpStatus code;
    public String message;
    GlobalMessage(HttpStatus code, String message) {
        this.code = code;
        this.message = message;
    }
}
