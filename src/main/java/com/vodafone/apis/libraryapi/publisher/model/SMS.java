package com.vodafone.apis.libraryapi.publisher.model;

import lombok.Data;

@Data
public class SMS {
    private String to;
    private String message;
}