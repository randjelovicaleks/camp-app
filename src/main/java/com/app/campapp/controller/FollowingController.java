package com.app.campapp.controller;

import com.app.campapp.service.impl.FollowingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/following", produces = MediaType.APPLICATION_JSON_VALUE)
public class FollowingController {

    @Autowired
    private FollowingServiceImpl followingServiceImpl;

    @PreAuthorize("hasRole('ROLE_CAMPER')")
    @PostMapping(value = "/{idFollower}/{idFollowed}")
    public ResponseEntity<?> follow(@PathVariable Long idFollower, @PathVariable Long idFollowed) {
        if (followingServiceImpl.follow(idFollower, idFollowed)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_CAMPER')")
    @GetMapping(value = "/{idFollower}/{idFollowed}")
    public ResponseEntity<?> alreadyFollow(@PathVariable Long idFollower, @PathVariable Long idFollowed) {
        return new ResponseEntity<>(followingServiceImpl.alreadyFollow(idFollower, idFollowed), HttpStatus.OK);
    }
}
