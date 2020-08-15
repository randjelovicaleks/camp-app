package com.app.campapp.service.impl;

import com.app.campapp.model.RegistrationRequest;
import com.app.campapp.model.VerificationToken;
import com.app.campapp.repository.RegistrationRequestRepository;
import com.app.campapp.repository.VerificationTokenRepository;
import com.app.campapp.service.RegistrationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.UUID;

@Service
public class RegistrationRequestServiceImpl implements RegistrationRequestService {

    @Autowired
    private RegistrationRequestRepository registrationRequestRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public RegistrationRequest createRequest(RegistrationRequest registrationRequest) {
        // work factor of bcrypt
        int strength = 10;
        // secureRandom() is salt generator
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
        registrationRequest.setPassword(bCryptPasswordEncoder.encode(registrationRequest.getPassword()));

        try {
            emailService.sendEmailForActivationAccount(registrationRequest);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return registrationRequestRepository.save(registrationRequest);
    }
}
