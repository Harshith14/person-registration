package com.sysco.test.registration.exception;

public class PersonNotFoundException extends Exception {
    public  PersonNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
