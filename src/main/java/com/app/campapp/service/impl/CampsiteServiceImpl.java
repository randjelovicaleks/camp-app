package com.app.campapp.service.impl;

import com.app.campapp.dto.CampsiteDTO;
import com.app.campapp.enums.ActivityType;
import com.app.campapp.enums.ReservationType;
import com.app.campapp.model.Camper;
import com.app.campapp.model.Campsite;
import com.app.campapp.model.Reservation;
import com.app.campapp.repository.CamperRepository;
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

    @Autowired
    private CamperRepository camperRepository;

    @Autowired
    private CamperCanRateServiceImpl camperCanRateServiceImpl;

    @Override
    public List<CampsiteDTO> simpleSearch(LocalDate dateFrom, LocalDate dateTo, String nearestCity) {
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
        List<Campsite> top3BestRated = campsiteRepository.bestRatedCampsites();
        List<CampsiteDTO> campsiteDTOS = new ArrayList<>();

        for (Campsite c : top3BestRated) {
            campsiteDTOS.add(new CampsiteDTO(c));
        }

        return campsiteDTOS;
    }

    @Override
    public CampsiteDTO addToFavourites(Long idCamper, Long idCampsite) {
        Camper camper = camperRepository.getOne(idCamper);
        Campsite campsite = campsiteRepository.getOne(idCampsite);

        if (camper != null && campsite != null) {
            camper.getFavouriteCampsites().add(campsite);
        }

        return new CampsiteDTO(campsite);
    }

    @Override
    public void removeFromFavourites(Long idCamper, Long idCampsite) {
        Camper camper = camperRepository.getOne(idCamper);
        Campsite campsite = campsiteRepository.getOne(idCampsite);
        Campsite campsiteForRemove = null;

        for (Campsite c : camper.getFavouriteCampsites()) {
            if (c.equals(campsite)) {
                campsiteForRemove = c;
            }
        }
        camper.getFavouriteCampsites().remove(campsiteForRemove);
        camperRepository.save(camper);
    }

    @Override
    public boolean rateCampsite(Long campsiteId, Long camperId, double rating) {
        Campsite campsite = campsiteRepository.getOne(campsiteId);

        if (campsite != null) {
            double newRating = (campsite.getRating() + rating) / 2;
            campsite.setRating(newRating);

            //promeniti da je kamper ocenio mesto za kampovanje
            if (camperCanRateServiceImpl.changeCamperCanRate(camperId, campsiteId)) {
                campsiteRepository.save(campsite);
                return true;
            }
        }
        return false;
    }

    @Override
    public void shareCampsite(Long idCampsite, Long idCamper) {
        Camper camper = camperRepository.getOne(idCamper);
        Campsite campsite = campsiteRepository.getOne(idCampsite);

        if (camper != null) {
            camper.getSharingCampsites().add(campsite);
            camperRepository.save(camper);
        }
    }

    @Override
    public void removeFromSharingCampsites(Long idCamper, Long idCampsite) {
        Camper camper = camperRepository.getOne(idCamper);
        Campsite campsite = campsiteRepository.getOne(idCampsite);
        Campsite campsiteForRemove = null;

        for (Campsite c : camper.getSharingCampsites()) {
            if (c.equals(campsite)) {
                campsiteForRemove = c;
            }
        }
        camper.getSharingCampsites().remove(campsiteForRemove);
        camperRepository.save(camper);
    }

    @Override
    public List<CampsiteDTO> getSharingCampsites(Long id) {
        Camper camper = camperRepository.getOne(id);
        List<CampsiteDTO> campsiteDTOS = new ArrayList<>();

        for (Campsite c : camper.getSharingCampsites()) {
            campsiteDTOS.add(new CampsiteDTO(c));
        }

        return campsiteDTOS;
    }

}
