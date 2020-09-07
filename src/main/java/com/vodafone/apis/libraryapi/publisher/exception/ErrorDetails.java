package com.vodafone.apis.libraryapi.publisher.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
