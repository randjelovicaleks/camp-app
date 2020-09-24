package com.app.campapp.service.impl;

import com.app.campapp.model.RegistrationRequest;
import com.app.campapp.model.Reservation;
import com.app.campapp.model.VerificationToken;
import com.app.campapp.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.core.env.Environment;
import java.util.UUID;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Async
    public void sendEmailForActivationAccount(RegistrationRequest registrationRequest) throws MailException, InterruptedException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(registrationRequest.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Activate account");
        VerificationToken token = new VerificationToken(UUID.randomUUID().toString(), registrationRequest);
        verificationTokenRepository.save(token);
        String link = "http://localhost:8081/activate-account?id=" + registrationRequest.getId() + "&token=" + token.getToken();
        mail.setText("Welcome to Camp App. " +
                "To activate your account you need to click on this link: " + link + ". For this action you have 24 hours.");
        javaMailSender.send(mail);
    }

    @Async
    public void sendEmailForRejectingReservation(Reservation reservation) throws MailException, InterruptedException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(reservation.getCamper().getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Reservation is rejected");
        mail.setText("We are sorry, but your reservation for campsite: " + reservation.getCampsite().getName() + " is rejected.");
        javaMailSender.send(mail);
    }
}
