package com.app.campapp.dto;

import com.app.campapp.model.*;
import java.time.LocalDate;

public class CampsiteDTO {

    private Long id;
    private String name;
    private String description;
    private String nearestCity;
    private boolean closeToMountain;
    private boolean closeToRiver;
    private LocalDate openingDate;
    private LocalDate closingDate;
    private int tentSpotsNumber;
    private double longitude;
    private double latitude;
    private double pricePerDay;
    private double rating;

    public CampsiteDTO() {
    }

    public CampsiteDTO(Campsite campsite) {
        this.id = campsite.getId();
        this.name = campsite.getName();
        this.description = campsite.getDescription();
        this.nearestCity = campsite.getNearestCity();
        this.closeToMountain = campsite.isCloseToMountain();
        this.closeToRiver = campsite.isCloseToRiver();
        this.openingDate = campsite.getOpeningDate();
        this.closingDate = campsite.getClosingDate();
        this.tentSpotsNumber = campsite.getTentSpotsNumber();
        this.longitude = campsite.getLongitude();
        this.latitude = campsite.getLatitude();
        this.pricePerDay = campsite.getPricePerDay();
        this.rating = campsite.getRating();
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

    public int getTentSpotsNumber() {
        return tentSpotsNumber;
    }

    public void setTentSpotsNumber(int tentSpotsNumber) {
        this.tentSpotsNumber = tentSpotsNumber;
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

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
