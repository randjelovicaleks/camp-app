package com.app.campapp.service;

import com.app.campapp.dto.CampsiteDTO;
import com.app.campapp.dto.StatisticDTO;
import com.app.campapp.enums.ActivityType;
import com.app.campapp.model.Campsite;
import java.time.LocalDate;
import java.util.List;

public interface CampsiteService {

    List<CampsiteDTO> simpleSearch(LocalDate dateFrom, LocalDate dateTo, String nearestCity);
    List<CampsiteDTO> extendedSearch(LocalDate dateFrom, LocalDate dateTo, String nearestCity, boolean closeToMountain,
                                     boolean closeToRiver, double priceFrom, double priceTo, List<ActivityType> activities);
    List<CampsiteDTO> bestRatedCampsites();
    boolean addToFavourites(Long idCamper, Long idCampsite);
    boolean removeFromFavourites(Long idCamper, Long idCampsite);
    boolean rateCampsite(Long idCampsite, Long idCamper, double rate);
    boolean shareCampsite(Long idCampsite, Long idCamper);
    boolean removeFromSharingCampsites(Long idCamper, Long idCampsite);
    List<Campsite> getSharedCampsites(Long idCamper);
    List<StatisticDTO> statisticForMostComments();
    List<StatisticDTO> statisticForMostReservations();
    Campsite getCampsite(Long id);
    Campsite createCampsite(CampsiteDTO campsiteDTO);
}
