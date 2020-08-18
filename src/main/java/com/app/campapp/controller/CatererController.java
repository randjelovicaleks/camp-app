package com.app.campapp.controller;

import com.app.campapp.dto.CatererDTO;
import com.app.campapp.service.impl.CatererServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/caterer", produces = MediaType.APPLICATION_JSON_VALUE)
public class CatererController {

    @Autowired
    private CatererServiceImpl catererServiceImpl;

    @PreAuthorize("hasRole('ROLE_CAMPER')")
    @PutMapping(value = "/{id}/report")
    public ResponseEntity<?> reportCaterer(@PathVariable Long id) {
        if (catererServiceImpl.reportCaterer(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<CatererDTO>> findAllCaterersWithReports(@PathVariable Long id) {
        List<CatererDTO> catererDTOS = catererServiceImpl.findAllCaterersWithReports();

        if (!catererDTOS.isEmpty()) {
            return new ResponseEntity<>(catererDTOS, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
