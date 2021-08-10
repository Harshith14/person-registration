package com.sysco.test.registration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PersonAlreadyExistsException.class)
    public ResponseEntity personAlreadyExistsException(PersonAlreadyExistsException e){
        return  new ResponseEntity("Person already exists", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity personNotFoundException(PersonNotFoundException e){
        return new ResponseEntity("Person not found",HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e){
        return new ResponseEntity("Mandatory field is missing", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity globalException(Exception e){
        return new ResponseEntity(e.getClass(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
