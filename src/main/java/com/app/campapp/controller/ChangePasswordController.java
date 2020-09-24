package com.app.campapp.controller;

import com.app.campapp.dto.ChangePasswordDTO;
import com.app.campapp.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping(value = "/change-password", produces = MediaType.APPLICATION_JSON_VALUE)
public class ChangePasswordController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CAMPER') or hasRole('ROLE_CATERER')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordDTO changePasswordDTO) {
        customUserDetailsService.changePassword(changePasswordDTO.getOldPassword(), changePasswordDTO.getNewPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
