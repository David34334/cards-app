package com.jdar.cardsapp.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CustomException extends RuntimeException {

    protected String customMessage;
    protected int httpStatus;

    public CustomException(String message) {
        super(message);
        this.customMessage = CustomException.class.getSimpleName();
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    public CustomException(String message, Throwable throwable) {
        super(message, throwable);
        this.customMessage = CustomException.class.getSimpleName();
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

}
