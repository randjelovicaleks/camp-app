package com.app.campapp.dto;

import com.app.campapp.enums.UserStatus;
import com.app.campapp.model.Caterer;

public class CatererDTO {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private UserStatus userStatus;
    private int numberOfReports;

    public CatererDTO() {
    }

    public CatererDTO(Caterer caterer) {
        this.id = caterer.getId();
        this.name = caterer.getName();
        this.surname = caterer.getSurname();
        this.email = caterer.getEmail();
        this.userStatus = caterer.getUserStatus();
        this.numberOfReports = caterer.getNumberOfReports();
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

    public int getNumberOfReports() {
        return numberOfReports;
    }

    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }
}
