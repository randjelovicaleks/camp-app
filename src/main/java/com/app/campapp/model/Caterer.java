package com.app.campapp.model;

import com.app.campapp.enums.UserStatus;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("CATERER")
public class Caterer extends User{

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @OneToMany(mappedBy = "caterer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Campsite> campsites;

    @OneToMany(mappedBy = "caterer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reply> replies;

    @Column(name = "number_of_reports")
    private int numberOfReports;

    public Caterer() {
    }

    public Caterer(Long id, String name, String surname, String email, String password, String phoneNumber,
                   UserStatus userStatus, int numberOfReports) {
        super(id, name, surname, email, password);
        this.phoneNumber = phoneNumber;
        this.userStatus = userStatus;
        this.campsites = new ArrayList<>();
        this.replies = new ArrayList<>();
        this.numberOfReports = numberOfReports;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public int getNumberOfReports() {
        return numberOfReports;
    }

    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }
}
