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
}
