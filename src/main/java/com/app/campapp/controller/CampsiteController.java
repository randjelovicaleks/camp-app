package com.app.campapp.controller;

import com.app.campapp.dto.CampsiteDTO;
import com.app.campapp.repository.CampsiteRepository;
import com.app.campapp.service.impl.CampsiteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    @GetMapping(value = "/{dateFromString}/{dateToString}/{nearestCity}")
    public ResponseEntity<?> simpleSearch(@PathVariable String dateFromString, @PathVariable String dateToString,
                                          @PathVariable String nearestCity) {
        LocalDate dateFrom = LocalDate.parse(dateFromString);
        LocalDate dateTo = LocalDate.parse(dateToString);

        return new ResponseEntity<>(campsiteServiceImpl.simpleSearch(dateFrom, dateTo, nearestCity), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_CATERER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_CAMPER')")
    @GetMapping(value = "/best/rated")
    public ResponseEntity<List<CampsiteDTO>> bestRatedCampsites() {
        return new ResponseEntity<>(campsiteServiceImpl.bestRatedCampsites(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_CAMPER')")
    @PutMapping(value = "/{idCampsite}/favourite/camper/{idCamper}")
    public ResponseEntity<CampsiteDTO> addToFavourites(@PathVariable Long idCamper, @PathVariable Long idCampsite) {
        return new ResponseEntity<>(campsiteServiceImpl.addToFavourites(idCamper, idCampsite), HttpStatus.OK);
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
        campsiteServiceImpl.removeFromFavourites(idCamper, idCampsite);
        return new ResponseEntity<>(HttpStatus.GONE);
    }

    @PreAuthorize("hasRole('ROLE_CAMPER')")
    @DeleteMapping(value = "/{idCampsite}/sharing/camper/{idCamper}")
    public ResponseEntity<?> removeFromSharingCampsites(@PathVariable Long idCamper, @PathVariable Long idCampsite) {
        campsiteServiceImpl.removeFromSharingCampsites(idCamper, idCampsite);
        return new ResponseEntity<>(HttpStatus.GONE);
    }
}
