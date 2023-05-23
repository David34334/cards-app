package com.jdar.cardsapp.config;

import com.jdar.cardsapp.exceptions.CustomException;
import com.jdar.cardsapp.models.dto.DataResponse;
import com.jdar.cardsapp.models.dto.Error;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Configuration
public class ExceptionHandlerConfiguration extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public final ResponseEntity<DataResponse<Object>> handleAllExceptions(CustomException customException) {
        Error errorDetails = Error.builder()
                .httpStatus(customException.getHttpStatus())
                .description(customException.getMessage())
                .date(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(DataResponse.builder().error(errorDetails).build(), HttpStatus.OK);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<DataResponse<Object>> handleContraintViolationExceptions(ConstraintViolationException constraintViolationException) {
        Error errorDetails = Error.builder()
                .httpStatus(400)
                .description(constraintViolationException.getMessage())
                .date(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(DataResponse.builder().error(errorDetails).build(), HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Error errorDetails = Error.builder()
                .httpStatus(400)
                .description(ex.getMessage())
                .date(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(DataResponse.builder().error(errorDetails).build(), HttpStatus.BAD_REQUEST);
    }

}