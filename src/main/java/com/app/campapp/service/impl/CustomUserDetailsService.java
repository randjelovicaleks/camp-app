package com.app.campapp.service.impl;

import com.app.campapp.model.User;
import com.app.campapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with email '%s'.", email));
        } else {
            return user;
        }
    }

    public void changePassword(String oldPassword, String newPassword) {

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String email = currentUser.getName();

        if (authenticationManager != null) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, oldPassword));
        } else {
            return;
        }

        User user = (User) loadUserByUsername(email);

        // work factor of bcrypt
        int strength = 10;
        // secureRandom() is salt generator
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        userRepository.save(user);

    }
}
