package com.jdar.cardsapp.exceptions.badrequest;

import com.jdar.cardsapp.exceptions.CustomException;
import org.springframework.http.HttpStatus;

public class BadRequestException extends CustomException {

    public BadRequestException(String message) {
        super(message);
        this.customMessage = BadRequestException.class.getSimpleName();
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    public BadRequestException(String message, Throwable throwable) {
        super(message, throwable);
        this.customMessage = BadRequestException.class.getSimpleName();
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

}
