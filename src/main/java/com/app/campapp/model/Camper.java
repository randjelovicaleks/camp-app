package com.app.campapp.model;

import com.app.campapp.enums.UserStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("CAMPER")
public class Camper extends User{

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @ManyToMany(mappedBy = "campers")
    private List<Campsite> favouriteCampsites;

    @OneToMany(mappedBy = "camper", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "camper", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    public Camper() {
    }

    public Camper(Long id, String name, String surname, String email, String password, UserStatus userStatus) {
        super(id, name, surname, email, password);
        this.userStatus = userStatus;
        this.favouriteCampsites = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public List<Campsite> getFavouriteCampsites() {
        return favouriteCampsites;
    }

    public void setFavouriteCampsites(List<Campsite> favouriteCampsites) {
        this.favouriteCampsites = favouriteCampsites;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
