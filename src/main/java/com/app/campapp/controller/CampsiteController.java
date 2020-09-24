package com.app.campapp.controller;

import com.app.campapp.dto.CampsiteDTO;
import com.app.campapp.dto.StatisticDTO;
import com.app.campapp.enums.ActivityType;
import com.app.campapp.model.Campsite;
import com.app.campapp.repository.CampsiteRepository;
import com.app.campapp.service.impl.CampsiteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/campsite", produces = MediaType.APPLICATION_JSON_VALUE)
public class CampsiteController {

    @Autowired
    private CampsiteServiceImpl campsiteServiceImpl;

    @Autowired
    private CampsiteRepository campsiteRepository;


    @PreAuthorize("hasRole('ROLE_CATERER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_CAMPER')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<CampsiteDTO> getCampsite(@PathVariable Long id) {
        Campsite campsite = campsiteServiceImpl.getCampsite(id);
        return new ResponseEntity<>(new CampsiteDTO(campsite), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_CATERER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_CAMPER')")
    @GetMapping(value = "/{dateFromString}/{dateToString}/{nearestCity}")
    public ResponseEntity<?> simpleSearch(@PathVariable String dateFromString, @PathVariable String dateToString,
                                          @PathVariable String nearestCity) {
        LocalDate dateFrom = LocalDate.parse(dateFromString);
        LocalDate dateTo = LocalDate.parse(dateToString);

        return new ResponseEntity<>(campsiteServiceImpl.simpleSearch(dateFrom, dateTo, nearestCity), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_CATERER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_CAMPER')")
    @GetMapping(value = "/search/{dateFromString}/{dateToString}/{nearestCity}/{closeToMountain}/{closeToRiver}/{priceFrom}/{priceTo}")
    public ResponseEntity<?> extendedSearch(@PathVariable String dateFromString, @PathVariable String dateToString,
                                          @PathVariable String nearestCity, @PathVariable Boolean closeToMountain,
                                        @PathVariable Boolean closeToRiver, @PathVariable double priceFrom, @PathVariable double priceTo) {
        LocalDate dateFrom = LocalDate.parse(dateFromString);
        LocalDate dateTo = LocalDate.parse(dateToString);
        List<ActivityType> activities = new ArrayList<>();

        return new ResponseEntity<>(campsiteServiceImpl.extendedSearch(dateFrom, dateTo, nearestCity, closeToMountain, closeToRiver, priceFrom, priceTo, activities), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_CATERER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_CAMPER')")
    @GetMapping(value = "/search/{dateFromString}/{dateToString}/{nearestCity}/{closeToMountain}/{closeToRiver}/{priceFrom}/{priceTo}/{activities}")
    public ResponseEntity<?> extendedSearchWithActivities(@PathVariable String dateFromString, @PathVariable String dateToString,
                                            @PathVariable String nearestCity, @PathVariable Boolean closeToMountain,
                                            @PathVariable Boolean closeToRiver, @PathVariable double priceFrom, @PathVariable double priceTo, @PathVariable List<ActivityType> activities) {
        LocalDate dateFrom = LocalDate.parse(dateFromString);
        LocalDate dateTo = LocalDate.parse(dateToString);

        return new ResponseEntity<>(campsiteServiceImpl.extendedSearch(dateFrom, dateTo, nearestCity, closeToMountain, closeToRiver, priceFrom, priceTo, activities), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_CATERER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_CAMPER')")
    @GetMapping(value = "/best/rated")
    public ResponseEntity<List<CampsiteDTO>> bestRatedCampsites() {
        return new ResponseEntity<>(campsiteServiceImpl.bestRatedCampsites(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_CATERER') or hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/most/comment")
    public ResponseEntity<List<StatisticDTO>> getCampsitesWithMostComments() {
        return new ResponseEntity<>(campsiteServiceImpl.statisticForMostComments(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_CATERER') or hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/most/reservations")
    public ResponseEntity<List<StatisticDTO>> getCampsitesWithMostReservations() {
        return new ResponseEntity<>(campsiteServiceImpl.statisticForMostReservations(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_CAMPER')")
    @PutMapping(value = "/{idCampsite}/favourite/camper/{idCamper}")
    public ResponseEntity<?> addToFavourites(@PathVariable Long idCamper, @PathVariable Long idCampsite) {
        if (campsiteServiceImpl.addToFavourites(idCamper, idCampsite)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_CAMPER')")
    @PutMapping(value="/camper/{camperId}/campsite/{campsiteId}/{rating}")
    public ResponseEntity<?> rating(@PathVariable Long camperId, @PathVariable Long campsiteId, @PathVariable double rating) {

        if (campsiteServiceImpl.rateCampsite(campsiteId, camperId, rating)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_CAMPER')")
    @DeleteMapping(value = "/{idCampsite}/favourite/camper/{idCamper}")
    public ResponseEntity<?> removeFromFavourites(@PathVariable Long idCamper, @PathVariable Long idCampsite) {
        if (campsiteServiceImpl.removeFromFavourites(idCamper, idCampsite)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_CAMPER')")
    @GetMapping(value = "/shared/camper/{id}")
    public ResponseEntity<List<CampsiteDTO>> getSharedCampsites(@PathVariable Long id) {
        List<Campsite> sharedCampsites = campsiteServiceImpl.getSharedCampsites(id);
        List<CampsiteDTO> campsiteDTOS = new ArrayList<>();

        for (Campsite c : sharedCampsites) {
            campsiteDTOS.add(new CampsiteDTO(c));
        }
        return new ResponseEntity<>(campsiteDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_CAMPER')")
    @PutMapping(value = "/{idCampsite}/sharing/camper/{idCamper}")
    public ResponseEntity<?> shareCampsite(@PathVariable Long idCamper, @PathVariable Long idCampsite) {
        if(campsiteServiceImpl.shareCampsite(idCampsite, idCamper)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_CAMPER')")
    @DeleteMapping(value = "/{idCampsite}/sharing/camper/{idCamper}")
    public ResponseEntity<?> removeFromSharingCampsites(@PathVariable Long idCamper, @PathVariable Long idCampsite) {
        if(campsiteServiceImpl.removeFromSharingCampsites(idCamper, idCampsite)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_CATERER')")
    @PostMapping
    public ResponseEntity<CampsiteDTO> createCampsite(@Valid @RequestBody CampsiteDTO campsiteDTO) {
        Campsite campsite = campsiteServiceImpl.createCampsite(campsiteDTO);
        return new ResponseEntity<>(new CampsiteDTO(campsite), HttpStatus.CREATED);
    }
}
