package com.nocountry.api.exception;

public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException(){

    }

    public InvalidPasswordException(String message){
        
    }

    public InvalidPasswordException(String message, Throwable cause){
        super(message, cause);
    }


}
