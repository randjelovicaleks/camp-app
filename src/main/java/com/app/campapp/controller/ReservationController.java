package com.app.campapp.controller;

import com.app.campapp.service.impl.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/reservation", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationController {

    @Autowired
    private ReservationServiceImpl reservationServiceImpl;

    @PutMapping(value = "/{id}/accept")
    public ResponseEntity<?> acceptReservation(@PathVariable Long id) {
        if (reservationServiceImpl.acceptReservation(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/{id}/reject")
    public ResponseEntity<?> rejectReservation(@PathVariable Long id) {
        if (reservationServiceImpl.rejectReservation(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/{id}/cancel")
    public ResponseEntity<?> cancelReservation(@PathVariable Long id) {
        if (reservationServiceImpl.cancelReservation(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
