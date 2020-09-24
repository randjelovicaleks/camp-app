package com.app.campapp.controller;

import com.app.campapp.dto.CatererDTO;
import com.app.campapp.model.Caterer;
import com.app.campapp.service.impl.CatererServiceImpl;
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
@RequestMapping(value = "/caterer", produces = MediaType.APPLICATION_JSON_VALUE)
public class CatererController {

    @Autowired
    private CatererServiceImpl catererServiceImpl;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CAMPER') or hasRole('ROLE_CATERER')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<CatererDTO> getCaterer(@PathVariable Long id) {
        Caterer caterer = catererServiceImpl.getCaterer(id);

        if (caterer != null) {
            return new ResponseEntity<>(new CatererDTO(caterer), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<CatererDTO>> getAllCaterers() {
        List<Caterer> caterers = catererServiceImpl.getAllCaterers();
        List<CatererDTO> catererDTOS = new ArrayList<>();

        for (Caterer c : caterers) {
            catererDTOS.add(new CatererDTO(c));
        }

        return new ResponseEntity<>(catererDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_CAMPER')")
    @PutMapping(value = "/{catererId}/camper/{camperId}/report")
    public ResponseEntity<?> reportCaterer(@PathVariable Long catererId, @PathVariable Long camperId) {
        if (catererServiceImpl.reportCaterer(catererId, camperId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/report")
    public ResponseEntity<List<CatererDTO>> findAllCaterersWithReports() {
        List<Caterer> caterers = catererServiceImpl.getCaterersWithReports();
        List<CatererDTO> catererDTOS = new ArrayList<>();

        for (Caterer c : caterers) {
            catererDTOS.add(new CatererDTO(c));
        }
        return new ResponseEntity<>(catererDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}/block")
    public ResponseEntity<?> blockCaterer(@PathVariable Long id) {
        if (catererServiceImpl.blockCaterer(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/notification")
    public ResponseEntity<?> reportedCaterers() {
        return new ResponseEntity<>(catererServiceImpl.reportedCaterers(), HttpStatus.OK);
    }

}
