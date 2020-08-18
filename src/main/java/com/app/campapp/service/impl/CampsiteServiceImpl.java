package com.app.campapp.service.impl;

import com.app.campapp.dto.CampsiteDTO;
import com.app.campapp.enums.ActivityType;
import com.app.campapp.enums.ReservationType;
import com.app.campapp.model.Campsite;
import com.app.campapp.model.Reservation;
import com.app.campapp.repository.CampsiteRepository;
import com.app.campapp.service.CampsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CampsiteServiceImpl implements CampsiteService {

    @Autowired
    private CampsiteRepository campsiteRepository;

    @Override
    public List<CampsiteDTO> simpleSearch(LocalDate dateFrom, LocalDate dateTo, String nearestCity) {
        List<Campsite> campsites = campsiteRepository.simpleSerach(dateFrom, dateTo, nearestCity);
        List<CampsiteDTO> campsiteDTOS = new ArrayList<>();

        for (Campsite c : campsites) {
            int counter = 0;
            for (Reservation r : c.getReservations()) {
                System.out.println("Date from: " + dateFrom);
                System.out.println("Date to: " + dateTo);
                System.out.println("Start date: " + r.getStartDate());
                System.out.println("End date: " + r.getEndDate());
                if (r.getReservationType().equals(ReservationType.ACCEPTED) || r.getReservationType().equals(ReservationType.PENDING)) {
                    if (r.getStartDate().isEqual(dateFrom) && r.getEndDate().isEqual(dateTo)) {
                        counter++;
                    } else if (r.getStartDate().isBefore(dateFrom) && r.getEndDate().isAfter(dateTo)) {
                        counter++;
                    } else if (r.getStartDate().isEqual(dateFrom) && r.getEndDate().isAfter(dateTo)) {
                        counter++;
                    } else if (r.getStartDate().isBefore(dateFrom) && r.getEndDate().isAfter(dateFrom)) {
                        counter++;
                    } else if (r.getStartDate().isBefore(dateTo) && r.getEndDate().isAfter(dateTo)) {
                        counter++;
                    }
                }
            }
            if (c.getTentSpotsNumber() > counter) {
                campsiteDTOS.add(new CampsiteDTO(c));
            }
        }
        return campsiteDTOS;
    }

    @Override
    public List<CampsiteDTO> extendedSearch(LocalDate dateFrom, LocalDate dateTo, String nearestCity, boolean closeToMountain,
                                         boolean closeToRiver, double priceFrom, double priceTo, List<ActivityType> activities) {
        List<Campsite> campsites = campsiteRepository.simpleSerach(dateFrom, dateTo, nearestCity);
        List<CampsiteDTO> campsiteDTOS = new ArrayList<>();

        for (Campsite c : campsites) {
            int counter = 0;
            for (Reservation r : c.getReservations()) {
                if (r.getReservationType().equals(ReservationType.ACCEPTED) || r.getReservationType().equals(ReservationType.PENDING)) {
                    if (r.getStartDate().isEqual(dateFrom) && r.getEndDate().isEqual(dateTo)) {
                        counter++;
                    } else if (r.getStartDate().isBefore(dateFrom) && r.getEndDate().isAfter(dateTo)) {
                        counter++;
                    } else if (r.getStartDate().isEqual(dateFrom) && r.getEndDate().isAfter(dateTo)) {
                        counter++;
                    } else if (r.getStartDate().isBefore(dateFrom) && r.getEndDate().isAfter(dateFrom)) {
                        counter++;
                    } else if (r.getStartDate().isBefore(dateTo) && r.getEndDate().isAfter(dateTo)) {
                        counter++;
                    }
                }
            }
            if (c.getTentSpotsNumber() > counter) {
                campsiteDTOS.add(new CampsiteDTO(c));
            }
        }
        return campsiteDTOS;
    }

    @Override
    public List<CampsiteDTO> bestRatedCampsites() {
        List<Campsite> top3BestRated = campsiteRepository.top3BestRated();
        List<CampsiteDTO> campsiteDTOS = new ArrayList<>();

        for (Campsite c : top3BestRated) {
            campsiteDTOS.add(new CampsiteDTO(c));
        }

        return campsiteDTOS;
    }
}