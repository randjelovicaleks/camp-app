package com.app.campapp.service;

import com.app.campapp.dto.CampsiteDTO;
import com.app.campapp.enums.ActivityType;
import java.time.LocalDate;
import java.util.List;

public interface CampsiteService {

    List<CampsiteDTO> simpleSearch(LocalDate dateFrom, LocalDate dateTo, String nearestCity);
    List<CampsiteDTO> extendedSearch(LocalDate dateFrom, LocalDate dateTo, String nearestCity, boolean closeToMountain,
                                     boolean closeToRiver, double priceFrom, double priceTo, List<ActivityType> activities);
    List<CampsiteDTO> bestRatedCampsites();
    CampsiteDTO addToFavourites(Long idCamper, Long idCampsite);
    void removeFromFavourites(Long idCamper, Long idCampsite);
    boolean rateCampsite(Long idCampsite, Long idCamper, double rate);
    void shareCampsite(Long idCampsite, Long idCamper);
    void removeFromSharingCampsites(Long idCamper, Long idCampsite);
    List<CampsiteDTO> getSharingCampsites(Long id);
}
