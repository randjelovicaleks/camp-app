package com.app.campapp.service.impl;

import com.app.campapp.dto.CampsiteDTO;
import com.app.campapp.dto.StatisticDTO;
import com.app.campapp.enums.ActivityType;
import com.app.campapp.enums.ReservationType;
import com.app.campapp.model.*;
import com.app.campapp.repository.CamperRepository;
import com.app.campapp.repository.CampsiteRepository;
import com.app.campapp.repository.CatererRepository;
import com.app.campapp.repository.ImageRepository;
import com.app.campapp.service.CampsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class CampsiteServiceImpl implements CampsiteService {

    @Autowired
    private CampsiteRepository campsiteRepository;

    @Autowired
    private CamperRepository camperRepository;

    @Autowired
    private CamperCanRateServiceImpl camperCanRateServiceImpl;

    @Autowired
    private CatererRepository catererRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<CampsiteDTO> simpleSearch(LocalDate dateFrom, LocalDate dateTo, String nearestCity) {
        List<Campsite> campsites = campsiteRepository.simpleSerach(dateFrom, dateTo, nearestCity);
        List<CampsiteDTO> campsiteDTOS = new ArrayList<>();

        for (Campsite c : campsites) {
            int counter = 0;
            if (c.isVisible()) {
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
            if (c.isVisible()) {
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

                if (c.isCloseToMountain() != closeToMountain) {
                    continue;
                }

                if (c.isCloseToRiver() != closeToRiver) {
                    continue;
                }

                if (priceFrom != 0) {
                    if (c.getPricePerDay() < priceFrom) {
                        continue;
                    }
                }

                if (priceTo != 0) {
                    if (c.getPricePerDay() > priceTo) {
                        continue;
                    }
                }

                if (!activities.isEmpty()) {
                    if (!c.getActivities().containsAll(activities)) {
                        continue;
                    }
                }

                if (c.getTentSpotsNumber() > counter) {
                    campsiteDTOS.add(new CampsiteDTO(c));
                }
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
    public boolean addToFavourites(Long idCamper, Long idCampsite) {
        Camper camper = camperRepository.getOne(idCamper);
        Campsite campsite = campsiteRepository.getOne(idCampsite);

        if (camper != null && campsite != null) {
            if (!camper.getFavouriteCampsites().contains(campsite) && !campsite.getCampers().contains(camper)) {
                camper.getFavouriteCampsites().add(campsite);
                campsite.getCampers().add(camper);
                camperRepository.save(camper);
                campsiteRepository.save(campsite);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean removeFromFavourites(Long idCamper, Long idCampsite) {
        Camper camper = camperRepository.getOne(idCamper);
        Campsite campsite = campsiteRepository.getOne(idCampsite);

        if (camper!= null && campsite != null) {
            camper.getFavouriteCampsites().remove(campsite);
            campsite.getCampers().remove(camper);
            camperRepository.save(camper);
            campsiteRepository.save(campsite);
            return true;
        }
        return false;
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
    public boolean shareCampsite(Long idCampsite, Long idCamper) {
        Camper camper = camperRepository.getOne(idCamper);
        Campsite campsite = campsiteRepository.getOne(idCampsite);
        List<Campsite> sharedCampsites = getSharedCampsites(idCamper);

        if (camper != null && campsite != null) {
            if (!sharedCampsites.contains(campsite)) {
                camper.getSharingCampsites().add(campsite);
                campsite.getSharingCampsitesCampers().add(camper);
                camperRepository.save(camper);
                campsiteRepository.save(campsite);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeFromSharingCampsites(Long idCamper, Long idCampsite) {
        Camper camper = camperRepository.getOne(idCamper);
        Campsite campsite = campsiteRepository.getOne(idCampsite);

        if (camper != null && campsite != null) {
            camper.getSharingCampsites().remove(campsite);
            campsite.getSharingCampsitesCampers().remove(camper);
            camperRepository.save(camper);
            campsiteRepository.save(campsite);
            return true;
        }
        return false;
    }

    @Override
    public List<Campsite> getSharedCampsites(Long idCamper) {
        List<Long> campsiteIds = campsiteRepository.findSharedCampsitesById(idCamper);
        List<Campsite> sharedCampsites = new ArrayList<>();

        for (Long id : campsiteIds) {
            Campsite campsite = campsiteRepository.getOne(id);
            sharedCampsites.add(campsite);
        }
        return sharedCampsites;
    }

    @Override
    public List<StatisticDTO> statisticForMostComments() {
        List<Campsite> campsites = campsiteRepository.findAll();
        List<StatisticDTO> statisticDTOS = new ArrayList<>();
        for (Campsite c : campsites) {
            StatisticDTO statisticDTO = new StatisticDTO();
            statisticDTO.setCampsiteName(c.getName());
            statisticDTO.setNumberOfComments(c.getComments().size());
            statisticDTOS.add(statisticDTO);
        }

        // Sortira listu po broju komentara
        statisticDTOS.sort(Comparator.comparingInt(c -> c.getNumberOfComments()));
        return statisticDTOS.subList(statisticDTOS.size() - 3, statisticDTOS.size());
    }

    @Override
    public List<StatisticDTO> statisticForMostReservations() {
        List<Campsite> campsites = campsiteRepository.findAll();
        List<StatisticDTO> statisticDTOS = new ArrayList<>();

        for (Campsite c : campsites) {
            StatisticDTO statisticDTO = new StatisticDTO();
            statisticDTO.setCampsiteName(c.getName());
            statisticDTO.setNumberOfReservations(c.getReservations().size());
            statisticDTOS.add(statisticDTO);
        }
        // Sortira kamp mesta po broju rezervacija
        statisticDTOS.sort(Comparator.comparingInt(c -> c.getNumberOfReservations()));
        return statisticDTOS.subList(campsites.size() - 3, campsites.size());
    }

    @Override
    public Campsite getCampsite(Long id) {
        return campsiteRepository.getOne(id);
    }

    @Override
    public Campsite createCampsite(CampsiteDTO campsiteDTO) {

        Campsite campsite = new Campsite();
        campsite.setName(campsiteDTO.getName());
        campsite.setDescription(campsiteDTO.getDescription());
        campsite.setNearestCity(campsiteDTO.getNearestCity());
        campsite.setCloseToMountain(campsiteDTO.isCloseToMountain());
        campsite.setCloseToRiver(campsiteDTO.isCloseToRiver());
        campsite.setOpeningDate(campsiteDTO.getOpeningDate());
        campsite.setClosingDate(campsiteDTO.getClosingDate());
        campsite.setTentSpotsNumber(campsiteDTO.getTentSpotsNumber());
        campsite.setLongitude(campsiteDTO.getLongitude());
        campsite.setLatitude(campsiteDTO.getLatitude());
        campsite.setPricePerDay(campsiteDTO.getPricePerDay());
        campsite.setRating(0);
        Caterer caterer = catererRepository.getOne(campsiteDTO.getCatererDTO().getId());
        campsite.setCaterer(caterer);
        campsite.setActivities(campsiteDTO.getActivities());
        campsite.setVisible(true);

        if(campsite.getImages() != null) {
            for (String image : campsiteDTO.getImages()) {
                Image img = new Image(image, campsite);
                imageRepository.save(img);
                campsite.getImages().add(img);
            }
        } else {
            ArrayList<Image> images = new ArrayList<>();
            for (String image : campsiteDTO.getImages()) {
                Image img = new Image(image, campsite);
                imageRepository.save(img);
                images.add(img);
            }
            campsite.setImages(images);
        }

        return campsite;
    }

}
