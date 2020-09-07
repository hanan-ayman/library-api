package com.vodafone.apis.libraryapi.publisher.exception;

import org.springframework.http.HttpStatus;

public class LibraryResourceNotFoundException extends ApiBasexception {
    HttpStatus status;

    public LibraryResourceNotFoundException(String message) {
        super(message);
    }

    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND
                ;
    }
}
