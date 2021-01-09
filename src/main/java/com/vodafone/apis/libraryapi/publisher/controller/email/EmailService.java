package com.vodafone.apis.libraryapi.publisher.controller.email;

import org.springframework.stereotype.Component;

@Component
public interface EmailService {
    public void sendSimpleMessage(String to, String subject, String text);
}
