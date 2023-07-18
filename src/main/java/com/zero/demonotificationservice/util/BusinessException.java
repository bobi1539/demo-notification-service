package com.zero.demonotificationservice.util;

import com.zero.demonotificationservice.constant.GlobalMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BusinessException extends RuntimeException{

    private HttpStatus code;
    private String message;

    public BusinessException(GlobalMessage globalMessage) {
        super(globalMessage.message);
        this.code = globalMessage.code;
        this.message = globalMessage.message;
    }
}
