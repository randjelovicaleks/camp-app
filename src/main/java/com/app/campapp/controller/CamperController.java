package com.app.campapp.controller;

import com.app.campapp.service.impl.CamperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/camper", produces = MediaType.APPLICATION_JSON_VALUE)
public class CamperController {

    @Autowired
    private CamperServiceImpl camperServiceImpl;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}/block")
    public ResponseEntity<?> blockCamper(@PathVariable Long id) {
        if (camperServiceImpl.blockCamper(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
   // @PutMapping(value = "/{id}/block")
    public ResponseEntity<?> blockCamperWhoCanceledReservation(@PathVariable Long id) {
        if (camperServiceImpl.blockCamperWhoCanceledReservation(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
