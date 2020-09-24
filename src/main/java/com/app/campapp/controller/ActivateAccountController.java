package com.app.campapp.controller;

import com.app.campapp.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/activate-account", produces = MediaType.APPLICATION_JSON_VALUE)
public class ActivateAccountController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PutMapping(value = "/request/{id}/{token}")
    public ResponseEntity<?> activateAccount(@PathVariable Long id, @PathVariable String token) {
        if (userServiceImpl.activateAccount(id, token)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
