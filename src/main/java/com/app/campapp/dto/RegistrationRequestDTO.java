package com.app.campapp.dto;

import com.app.campapp.enums.UserType;
import com.app.campapp.model.RegistrationRequest;

public class RegistrationRequestDTO {

    private Long id;
    private UserType userType;
    private String name;
    private String surname;
    private String email;
    private String password;

    public RegistrationRequestDTO() {
    }

    public RegistrationRequestDTO(RegistrationRequest registrationRequest) {
        this.id = registrationRequest.getId();
        this.userType = registrationRequest.getUserType();
        this.name = registrationRequest.getName();
        this.surname = registrationRequest.getSurname();
        this.email = registrationRequest.getEmail();
        this.password = registrationRequest.getPassword();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
