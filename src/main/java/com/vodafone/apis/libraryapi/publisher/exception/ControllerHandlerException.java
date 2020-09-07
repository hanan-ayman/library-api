package com.vodafone.apis.libraryapi.publisher.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;hanan
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerHandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiBasexception.class)
    public ResponseEntity<ErrorDetails> handlerExceptionAPIGeneric(ApiBasexception ex , WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage() , request.getDescription(false));
        return new ResponseEntity<ErrorDetails>(errorDetails , ex.getStatus());
    }

    //Instead of duplicating the code of handleing exaption we create a errorbaseapi
//    @ExceptionHandler(LibraryResourceNotFoundException.class)
//    public ResponseEntity<ErrorDetails> handlerExceptionAPIGeneric(LibraryResourceNotFoundException ex , WebRequest request) {
//        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage() , request.getDescription(false));
//        return new ResponseEntity<ErrorDetails>(errorDetails , ex.getStatus());
//    }

}
