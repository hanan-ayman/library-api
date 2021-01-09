package com.vodafone.apis.libraryapi.publisher.controller.email;

import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
public interface EmailService {
    public void sendSimpleMessage(String to, String subject, String text) throws MessagingException;
}
