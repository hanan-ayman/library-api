package com.vodafone.apis.libraryapi.publisher.controller.JMS;

import com.vodafone.apis.libraryapi.publisher.controller.email.EmailService;
import com.vodafone.apis.libraryapi.publisher.controller.sms.MobileService;
import com.vodafone.apis.libraryapi.publisher.model.Publisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@Slf4j
public class JmsConsumer {
    final static String PUBLISHER_ADDED_MAIL_SUBJECT = "PUBLISHER ADDED SUCCESSFULLY";
    final static String PUBLISHER_ADDED_MAIL_BODY = "Dear Customer , <b>congratulation</b> ! your publisher has been added successfully";

    @Autowired
    private MobileService mobileService;
    @Autowired
    private EmailService emailService;

    @JmsListener(destination = "${activemq.queue.name}")
    public void receiveSMS(Publisher publisher) {
        try {
            mobileService.sendSMS(publisher.getPhoneNumber());
            emailService.sendSimpleMessage(publisher.getEmailId(), PUBLISHER_ADDED_MAIL_SUBJECT, PUBLISHER_ADDED_MAIL_BODY);
        } catch (MessagingException e){
            log.error("we could not send your email  {} " , e.getMessage());
        }
        log.info("Received message='{}' , '{}'", publisher.getPhoneNumber() , publisher.getEmailId());
    }
}
