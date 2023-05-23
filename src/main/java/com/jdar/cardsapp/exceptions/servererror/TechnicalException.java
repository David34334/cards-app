package com.jdar.cardsapp.exceptions.servererror;

import com.jdar.cardsapp.exceptions.CustomException;
import org.springframework.http.HttpStatus;

public class TechnicalException extends CustomException {

    public TechnicalException(String message) {
        super(message);
        super.customMessage = TechnicalException.class.getSimpleName();
        super.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    public TechnicalException(String message, Throwable throwable) {
        super(message, throwable);
        super.customMessage = TechnicalException.class.getSimpleName();
        super.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

}
