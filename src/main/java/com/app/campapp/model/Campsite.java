package com.app.campapp.model;

import com.app.campapp.enums.ActivityType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Campsite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "nearest_city", nullable = false)
    private String nearestCity;

    @Column(name = "close_to_mountain", nullable = false)
    private boolean closeToMountain;

    @Column(name = "close_to_river", nullable = false)
    private boolean closeToRiver;

    @Column(name = "opening_date", nullable = false)
    private LocalDate openingDate;

    @Column(name = "closing_date", nullable = false)
    private LocalDate closingDate;

    @Column(name = "spots_number", nullable = false)
    private int spotsNumber;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection(targetClass=ActivityType.class)
    @CollectionTable(name = "activity_type", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private List<ActivityType> activities;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Caterer caterer;

    @OneToMany(mappedBy = "campsite", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "campsite", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Statistic statistic;

    @ManyToMany
    @JoinTable(name = "favourite_campsites",
            joinColumns = @JoinColumn(name = "campsite_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "camper_id", referencedColumnName = "id"))
    private List<Camper> campers;

    public Campsite() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNearestCity() {
        return nearestCity;
    }

    public void setNearestCity(String nearestCity) {
        this.nearestCity = nearestCity;
    }

    public boolean isCloseToMountain() {
        return closeToMountain;
    }

    public void setCloseToMountain(boolean closeToMountain) {
        this.closeToMountain = closeToMountain;
    }

    public boolean isCloseToRiver() {
        return closeToRiver;
    }

    public void setCloseToRiver(boolean closeToRiver) {
        this.closeToRiver = closeToRiver;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public LocalDate getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDate closingDate) {
        this.closingDate = closingDate;
    }

    public int getSpotsNumber() {
        return spotsNumber;
    }

    public void setSpotsNumber(int spotsNumber) {
        this.spotsNumber = spotsNumber;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public List<ActivityType> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivityType> activities) {
        this.activities = activities;
    }

    public Caterer getCaterer() {
        return caterer;
    }

    public void setCaterer(Caterer caterer) {
        this.caterer = caterer;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Statistic getStatistic() {
        return statistic;
    }

    public void setStatistic(Statistic statistic) {
        this.statistic = statistic;
    }

    public List<Camper> getCampers() {
        return campers;
    }

    public void setCampers(List<Camper> campers) {
        this.campers = campers;
    }
}
