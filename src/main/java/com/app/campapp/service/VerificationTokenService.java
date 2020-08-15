package com.app.campapp.service;

import com.app.campapp.model.VerificationToken;

public interface VerificationTokenService {

    VerificationToken findByToken(String token);
}
