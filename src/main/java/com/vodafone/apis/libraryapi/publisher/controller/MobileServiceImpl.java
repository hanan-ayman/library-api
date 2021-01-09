package com.vodafone.apis.libraryapi.publisher.controller;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.vodafone.apis.libraryapi.publisher.controller.sms.MobileService;
import org.springframework.stereotype.Component;

@Component
public class MobileServiceImpl implements MobileService {
    final static String ACCOUNT_SID = "ACe4d8891a3b61ec5534715dee84ba41a1";
    final static String AUTH_TOKEN =  "d63585f1bff8c424e42a98a43f0bd916";
    final static String Twilio_Number = "12019480779";
    final static String TEXT_SMS = "Your publisher has been added";
    public void sendSMS(String to){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(new PhoneNumber(to),
                new PhoneNumber(Twilio_Number), TEXT_SMS).create();
//        System.out.println(message.getSid() + message.getStatus());
    }
}
