package com.app.campapp.controller;

import com.app.campapp.dto.CamperDTO;
import com.app.campapp.model.Camper;
import com.app.campapp.service.impl.CamperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/camper", produces = MediaType.APPLICATION_JSON_VALUE)
public class CamperController {

    @Autowired
    private CamperServiceImpl camperServiceImpl;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CAMPER') or hasRole('ROLE_CATERER')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<CamperDTO> getCamper(@PathVariable Long id) {
        Camper camper = camperServiceImpl.getCamper(id);
        return new ResponseEntity<>(new CamperDTO(camper), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<CamperDTO>> getAllCampers() {
        List<Camper> campers = camperServiceImpl.findAll();
        List<CamperDTO> camperDTOS = new ArrayList<>();

        for (Camper c : campers) {
            camperDTOS.add(new CamperDTO(c));
        }
        return new ResponseEntity<>(camperDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/canceled/reservation")
    public ResponseEntity<List<CamperDTO>> getCampersWithCanceledReservations() {
        List<Camper> campers = camperServiceImpl.findCampersWhoCanceledReservations();
        List<CamperDTO> camperDTOS = new ArrayList<>();

        for (Camper c : campers) {
            camperDTOS.add(new CamperDTO(c));
        }
        return new ResponseEntity<>(camperDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}/block")
    public ResponseEntity<?> blockCamper(@PathVariable Long id) {
        if (camperServiceImpl.blockCamper(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/notification")
    public ResponseEntity<?> campersWithCanceledReservations() {
        return new ResponseEntity<>(camperServiceImpl.campersWhoCanceledReservations(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_CAMPER')")
    @GetMapping(value = "/{idFollowed}/followers")
    public ResponseEntity<List<CamperDTO>> getFollowers(@PathVariable Long idFollowed) {
        List<Camper> followers = camperServiceImpl.getFollowers(idFollowed);
        List<CamperDTO> followersDTO = new ArrayList<>();

        for (Camper camper : followers) {
            followersDTO.add(new CamperDTO(camper));
        }
        return new ResponseEntity<>(followersDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_CAMPER')")
    @GetMapping(value = "/{idFollower}/following")
    public ResponseEntity<List<CamperDTO>> getFollowing(@PathVariable Long idFollower) {
        List<Camper> following = camperServiceImpl.getFollowing(idFollower);
        List<CamperDTO> followingDTO = new ArrayList<>();

        for (Camper camper : following) {
            followingDTO.add(new CamperDTO(camper));
        }
        return new ResponseEntity<>(followingDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_CAMPER')")
    @GetMapping(value = "/{name}/{surname}")
    public ResponseEntity<List<CamperDTO>> findByNameAndSurname(@PathVariable String name, @PathVariable String surname) {
        List<Camper> campers = camperServiceImpl.findByNameAndSurname(name.toLowerCase(), surname.toLowerCase());
        List<CamperDTO> camperDTOS = new ArrayList<>();

        for (Camper camper : campers) {
            camperDTOS.add(new CamperDTO(camper));
        }
        return new ResponseEntity<>(camperDTOS, HttpStatus.OK);
    }
}
