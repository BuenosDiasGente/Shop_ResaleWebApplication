package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class TestExceptionHandler {
    //жестко сетим сообщение
//      @ExceptionHandler(NotFoundConfigException.class)
//    public ResponseEntity<?> exceptionProcessing(NotFoundConfigException exception){
//    return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getNOT_FOUND_EXCEPTION_DESCRIPTION());
//    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<?> exceptionProcessing(InvalidPasswordException exception){
//передаем сообщение которое у нас в константах
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
