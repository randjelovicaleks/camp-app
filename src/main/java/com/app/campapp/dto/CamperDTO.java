package com.app.campapp.dto;

import com.app.campapp.enums.UserStatus;
import com.app.campapp.model.Camper;

public class CamperDTO {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private UserStatus userStatus;

    public CamperDTO() {
    }

    public CamperDTO(Camper camper) {
        this.id = camper.getId();
        this.name = camper.getName();
        this.surname = camper.getSurname();
        this.email = camper.getEmail();
        this.userStatus = camper.getUserStatus();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }
}
