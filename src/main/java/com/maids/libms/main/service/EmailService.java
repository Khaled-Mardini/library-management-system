package com.maids.libms.main.service;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Async
    public void sendVerificationEmail(String to, String verificationCode) {
        String subject = "Backend Quiz Verification Email";
        String body = "Your verification code is " + verificationCode;
        log.info("sending email " + to);
        sendEmail(to, subject, body);
        log.info("Email sent successfully" + to);
    }

    @Async
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        javaMailSender.send(mailMessage);
    }
}