package com.app.campapp.controller;

import com.app.campapp.service.impl.CamperCanRateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/rating", produces = MediaType.APPLICATION_JSON_VALUE)
public class CamperCanRateController {

    @Autowired
    private CamperCanRateServiceImpl camperCanRateServiceImpl;

    @PreAuthorize("hasRole('ROLE_CAMPER')")
    @GetMapping(value= "/camper/{camperId}/campsite/{campsiteId}")
    public ResponseEntity<?> camperCanRate(@PathVariable Long camperId, @PathVariable Long campsiteId) {
        return new ResponseEntity<>(camperCanRateServiceImpl.camperCanRate(camperId, campsiteId), HttpStatus.OK);
    }

}
