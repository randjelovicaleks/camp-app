package com.app.campapp.controller;

import com.app.campapp.service.impl.CamperCanCommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/commenting", produces = MediaType.APPLICATION_JSON_VALUE)
public class CamperCanCommentController {

    @Autowired
    private CamperCanCommentServiceImpl camperCanCommentServiceImpl;

    @PreAuthorize("hasRole('ROLE_CAMPER')")
    @GetMapping(value= "/camper/{camperId}/campsite/{campsiteId}")
    public ResponseEntity<?> camperCanComment(@PathVariable Long camperId, @PathVariable Long campsiteId) {
        return new ResponseEntity<>(camperCanCommentServiceImpl.camperCanComment(camperId, campsiteId), HttpStatus.OK);
    }
}
