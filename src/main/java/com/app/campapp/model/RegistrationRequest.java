package com.app.campapp.model;

import com.app.campapp.dto.RegistrationRequestDTO;
import com.app.campapp.enums.UserType;

import javax.persistence.*;

@Entity
public class RegistrationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    public RegistrationRequest() {
    }

    public RegistrationRequest(RegistrationRequestDTO registrationRequestDTO) {
        this.id = registrationRequestDTO.getId();
        this.userType = registrationRequestDTO.getUserType();
        this.name = registrationRequestDTO.getName();
        this.surname = registrationRequestDTO.getSurname();
        this.email = registrationRequestDTO.getEmail();
        this.password = registrationRequestDTO.getPassword();
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
