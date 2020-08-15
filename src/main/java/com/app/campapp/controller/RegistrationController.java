package com.app.campapp.controller;

import com.app.campapp.dto.RegistrationRequestDTO;
import com.app.campapp.model.RegistrationRequest;
import com.app.campapp.service.impl.RegistrationRequestServiceImpl;
import com.app.campapp.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistrationController {

    @Autowired
    private RegistrationRequestServiceImpl registrationRequestServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping(value = "/registration")
    public ResponseEntity<RegistrationRequestDTO> createRegistrationRequest(@RequestBody RegistrationRequestDTO registrationRequestDTO) {

        if (!userServiceImpl.emailExist(registrationRequestDTO.getEmail())) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        RegistrationRequest registrationRequest = new RegistrationRequest(registrationRequestDTO);

        if (registrationRequest != null) {
            registrationRequestServiceImpl.createRequest(registrationRequest);
            return new ResponseEntity<>(registrationRequestDTO, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(value = "/activate-account/request/{id}/{token}")
    public ResponseEntity<?> activateAccount(@PathVariable Long id, @PathVariable String token) {
        if (userServiceImpl.activateAccount(id, token)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
