package com.jdar.cardsapp.exceptions.successful;

import com.jdar.cardsapp.exceptions.CustomException;
import org.springframework.http.HttpStatus;

public class NoDataException extends CustomException {

    public NoDataException(String message) {
        super(message);
        super.customMessage = NoDataException.class.getSimpleName();
        super.httpStatus = HttpStatus.NO_CONTENT.value();
    }

    public NoDataException(String message, Throwable throwable) {
        super(message, throwable);
        super.customMessage = NoDataException.class.getSimpleName();
        super.httpStatus = HttpStatus.NO_CONTENT.value();
    }

}
