package com.zero.demonotificationservice.constant;

import org.springframework.http.HttpStatus;

public enum GlobalMessage {
    SUCCESS(HttpStatus.OK, "Success"),
    UUID_NOT_VALID(HttpStatus.BAD_REQUEST, "Uuid not valid"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "Data Not Found");

    public HttpStatus code;
    public String message;
    GlobalMessage(HttpStatus code, String message) {
        this.code = code;
        this.message = message;
    }
}
