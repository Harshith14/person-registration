package com.sysco.test.registration.exception;

public class PersonAlreadyExistsException extends Exception {
    public  PersonAlreadyExistsException(String errorMessage){
        super(errorMessage);
    }
}
