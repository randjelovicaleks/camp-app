package com.app.campapp.model;

import com.app.campapp.enums.UserStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("CATERER")
public class Caterer extends User{

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @OneToMany(mappedBy = "caterer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Campsite> campsites;

    @OneToMany(mappedBy = "caterer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments;

    public Caterer() {
    }

    public Caterer(Long id, String name, String surname, String email, String password, UserStatus userStatus) {
        super(id, name, surname, email, password);
        this.userStatus = userStatus;
        this.campsites = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public List<Campsite> getCampsites() {
        return campsites;
    }

    public void setCampsites(List<Campsite> campsites) {
        this.campsites = campsites;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
