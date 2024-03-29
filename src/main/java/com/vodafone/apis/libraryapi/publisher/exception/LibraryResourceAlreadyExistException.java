package com.vodafone.apis.libraryapi.publisher.exception;

import org.springframework.http.HttpStatus;

public class LibraryResourceAlreadyExistException extends ApiBasexception {
    HttpStatus status;
    public LibraryResourceAlreadyExistException(String message) {
        super(message);
    }

    public HttpStatus getStatus() {
        return HttpStatus.CONFLICT;
    }
}
