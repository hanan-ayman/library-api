package com.vodafone.apis.libraryapi.publisher.controller;

import com.vodafone.apis.libraryapi.publisher.controller.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public void sendSimpleMessage(
            String to, String subject, String text) throws MessagingException {
        //TODO: This is a simple way to send email without attachment
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("hanan.ayman.said@gmail.com");
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
//        emailSender.send(message);

//TODO: This is a simple way to send email with attachment
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);
        helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));
        javaMailSender.send(msg);
    }
}
