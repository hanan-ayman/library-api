package com.vodafone.apis.libraryapi.publisher.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class ErrorDetails {
    private String msg;
    private String uri;
    @JsonFormat (shape = JsonFormat.Shape.STRING , pattern = "dd:MM:yyyy")
    private Date timeStamp;

    private ErrorDetails() {
        this.timeStamp = new Date();
    }

    public ErrorDetails(String msg, String uri) {
        this();
        this.msg = msg;
        this.uri = uri;
    }
}
