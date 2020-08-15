package com.app.campapp.service;

public interface UserService {

    boolean emailExist(String email);
    boolean activateAccount(Long id, String token);
}
