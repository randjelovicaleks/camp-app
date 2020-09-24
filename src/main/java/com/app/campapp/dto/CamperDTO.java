package com.app.campapp.dto;

import com.app.campapp.enums.UserStatus;
import com.app.campapp.model.Camper;
import com.app.campapp.model.Campsite;

import java.util.ArrayList;
import java.util.List;

public class CamperDTO {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private UserStatus userStatus;
    private List<CampsiteDTO> sharedCampsites;
    private List<CampsiteDTO> favouriteCampsites;

    public CamperDTO() {
    }

    public CamperDTO(Camper camper) {
        this.id = camper.getId();
        this.name = camper.getName();
        this.surname = camper.getSurname();
        this.email = camper.getEmail();
        this.userStatus = camper.getUserStatus();
        this.sharedCampsites = new ArrayList<>();
        if(camper.getSharingCampsites() != null) {
            for(Campsite c1: camper.getSharingCampsites()) {
                this.sharedCampsites.add(new CampsiteDTO(c1));
            }
        }
        this.favouriteCampsites = new ArrayList<>();
        if(camper.getFavouriteCampsites() != null) {
            for (Campsite c2 : camper.getFavouriteCampsites()) {
                this.favouriteCampsites.add(new CampsiteDTO(c2));
            }
        }
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

    public List<CampsiteDTO> getSharedCampsites() {
        return sharedCampsites;
    }

    public void setSharedCampsites(List<CampsiteDTO> sharedCampsites) {
        this.sharedCampsites = sharedCampsites;
    }

    public List<CampsiteDTO> getFavouriteCampsites() {
        return favouriteCampsites;
    }

    public void setFavouriteCampsites(List<CampsiteDTO> favouriteCampsites) {
        this.favouriteCampsites = favouriteCampsites;
    }
}
