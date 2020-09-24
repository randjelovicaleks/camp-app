package com.app.campapp.service.impl;

import com.app.campapp.enums.UserStatus;
import com.app.campapp.enums.UserType;
import com.app.campapp.model.*;
import com.app.campapp.repository.*;
import com.app.campapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegistrationRequestRepository registrationRequestRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private CamperRepository camperRepository;

    @Autowired
    private CatererRepository catererRepository;

    @Override
    public boolean emailExist(String email) {
        User user = userRepository.findByEmail(email);
        RegistrationRequest registrationRequest = registrationRequestRepository.findByEmail(email);
        if (user != null || registrationRequest != null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean activateAccount(Long id, String token) {
        RegistrationRequest registrationRequest = registrationRequestRepository.getOne(id);
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);

        try {
            if (verificationToken != null) {
                Calendar cal = Calendar.getInstance();
                if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) >= 0) {
                    if (registrationRequest != null) {
                        if (registrationRequest.getUserType().equals(UserType.CAMPER)) {
                            Camper camper = new Camper();
                            camper.setName(registrationRequest.getName());
                            camper.setSurname(registrationRequest.getSurname());
                            camper.setEmail(registrationRequest.getEmail());
                            camper.setPassword(registrationRequest.getPassword());
                            camper.setEnabled(true);
                            List<Authority> auth = authorityService.findByRole("ROLE_CAMPER");
                            camper.setAuthorities(auth);
                            camper.setUserStatus(UserStatus.ACTIVE);
                            camperRepository.save(camper);
                            return true;
                        } else if (registrationRequest.getUserType().equals(UserType.CATERER)){
                            Caterer caterer = new Caterer();
                            caterer.setName(registrationRequest.getName());
                            caterer.setSurname(registrationRequest.getSurname());
                            caterer.setEmail(registrationRequest.getEmail());
                            caterer.setPassword(registrationRequest.getPassword());
                            caterer.setPhoneNumber(registrationRequest.getPhoneNumber());
                            caterer.setEnabled(true);
                            List<Authority> auth = authorityService.findByRole("ROLE_CATERER");
                            caterer.setAuthorities(auth);
                            caterer.setUserStatus(UserStatus.ACTIVE);
                            caterer.setNumberOfReports(0);
                            catererRepository.save(caterer);
                            return true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
