package com.app.campapp.dto;

import com.app.campapp.enums.UserType;
import com.app.campapp.model.RegistrationRequest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegistrationRequestDTO {

    private Long id;
    private UserType userType;

    @NotBlank(message="Name must not be empty.")
    @Pattern(regexp="[a-zA-Z ]+$", message="Name must not include special characters and numbers.")
    private String name;

    @NotBlank(message="Name must not be empty.")
    @Pattern(regexp="[a-zA-Z ]+$", message="Name must not include special characters and numbers.")
    private String surname;

    @NotBlank(message="Email must not be empty.")
    @Pattern(regexp="^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$",
            message="Email must contain only letters and numbers.")
    private String email;

    @NotBlank(message="Password must not be empty.")
    @Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}$",
            message="Password must contain at least 1 uppercase letter, 1 lowercase letter, 1 digit, " +
                    "1 special character and must be minimum 8 characters long")
    private String password;
    private String phoneNumber;

    public RegistrationRequestDTO() {
    }

    public RegistrationRequestDTO(RegistrationRequest registrationRequest) {
        this.id = registrationRequest.getId();
        this.userType = registrationRequest.getUserType();
        this.name = registrationRequest.getName();
        this.surname = registrationRequest.getSurname();
        this.email = registrationRequest.getEmail();
        this.password = registrationRequest.getPassword();
        this.phoneNumber = registrationRequest.getPhoneNumber();
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
