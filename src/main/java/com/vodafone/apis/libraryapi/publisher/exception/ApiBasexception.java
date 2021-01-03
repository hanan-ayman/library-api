package com.vodafone.apis.libraryapi.publisher.exception;

import org.springframework.http.HttpStatus;

public abstract class ApiBasexception extends Exception {
    HttpStatus status;
    String message;

    public ApiBasexception(String message) {
        super(message);
    }
    public abstract HttpStatus getStatus();
}
