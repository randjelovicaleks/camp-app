package com.app.campapp.controller;

import com.app.campapp.service.impl.CamperCanReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/report", produces = MediaType.APPLICATION_JSON_VALUE)
public class CamperCanReportController {

    @Autowired
    private CamperCanReportServiceImpl camperCanReportServiceImpl;

    @PreAuthorize("hasRole('ROLE_CAMPER')")
    @GetMapping(value= "/camper/{camperId}/caterer/{catererId}")
    public ResponseEntity<?> camperCanReport(@PathVariable Long camperId, @PathVariable Long catererId) {
        return new ResponseEntity<>(camperCanReportServiceImpl.camperCanReport(catererId, camperId), HttpStatus.OK);
    }
}
