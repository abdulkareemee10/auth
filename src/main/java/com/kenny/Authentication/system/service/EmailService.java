package com.kenny.Authentication.system.service;

import com.kenny.Authentication.system.dto.EmailDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j

public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
//our email sender will be the email that was given as a username in the application.properties file
    @Value("${spring.mail.username}")
    private String emailSender;


    public void sendEmail(EmailDetails emailDetails){

        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(emailSender);
            mailMessage.setTo(emailDetails.getRecipient());
            mailMessage.setText(emailDetails.getMessageBody());
            mailMessage.setSubject(emailDetails.getSubject());

            javaMailSender.send(mailMessage);
            log.info("message sent to {}", emailDetails.getRecipient() );
            log.info("message sent from {}", emailSender);
            log.info("message body {}", emailDetails.getMessageBody());
        } catch (MailException e) {
            throw new RuntimeException(e);
        }

    }


}
