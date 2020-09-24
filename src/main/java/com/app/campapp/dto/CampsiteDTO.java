package com.app.campapp.dto;

import com.app.campapp.enums.ActivityType;
import com.app.campapp.model.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CampsiteDTO {

    private Long id;

    @NotBlank(message="Name must not be empty.")
    @Pattern(regexp="[a-zA-Z ]+$", message="Name must not include special characters and numbers.")
    private String name;
    @NotBlank(message="Description must not be empty.")
    @Pattern(regexp="^[a-zA-Z0-9.,?! ]*$",
            message="Description must contain only letters, digits and punctuation marks.")
    private String description;
    @NotBlank(message="Nearest city must not be empty.")
    @Pattern(regexp="[a-zA-Z ]+$", message="Nearest city must not include special characters and numbers.")
    private String nearestCity;
    private boolean closeToMountain;
    private boolean closeToRiver;
    private LocalDate openingDate;
    private LocalDate closingDate;
    @NotNull
    private int tentSpotsNumber;
    private double longitude;
    private double latitude;
    @NotNull
    private double pricePerDay;
    private double rating;
    private CatererDTO catererDTO;
    private List<ActivityType> activities;
    private boolean visible;
    private List<String> images;

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
        this.catererDTO = new CatererDTO(campsite.getCaterer());
        this.activities = campsite.getActivities();
        this.visible = campsite.isVisible();
        this.images = new ArrayList<>();
        if(campsite.getImages() != null) {
            for (Image image : campsite.getImages()) {
                images.add(image.getImage());
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

    public CatererDTO getCatererDTO() {
        return catererDTO;
    }

    public void setCatererDTO(CatererDTO catererDTO) {
        this.catererDTO = catererDTO;
    }

    public List<ActivityType> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivityType> activities) {
        this.activities = activities;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
