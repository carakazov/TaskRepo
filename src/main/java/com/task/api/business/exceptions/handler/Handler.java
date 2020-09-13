package com.task.api.business.exceptions.handler;

import com.task.api.business.exceptions.IncorrectDateException;
import com.task.api.business.exceptions.ItemNotFoundException;
import com.task.api.business.exceptions.LoginAlreadyExistsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//Класс для отлова исключений, сгенерированных классом Validator.
@ControllerAdvice
public class Handler extends ResponseEntityExceptionHandler {
    //Перехват исключения ItemNotFoundException.
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<Object> handleItemNotFoundException(Exception ex, WebRequest request){
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    //Перехват исключения LoginAlreadyExistsException.
    @ExceptionHandler(LoginAlreadyExistsException.class)
    public ResponseEntity<Object> handleLoginAndPasswordExistsException(Exception ex, WebRequest request){
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_IMPLEMENTED, request);
    }

    //Перехват исключения IncorrectDateException.
    @ExceptionHandler(IncorrectDateException.class)
    public ResponseEntity<Object> handleIncorrectDateException(Exception ex, WebRequest request){
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_IMPLEMENTED, request);
    }

    //Перехват исключения DiskAlreadyTakenException.
    @ExceptionHandler
    public ResponseEntity<Object> handleDiskAlreadyTakenException(Exception ex, WebRequest request){
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_IMPLEMENTED, request);
    }
}
