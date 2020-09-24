package com.app.campapp.controller;

import com.app.campapp.dto.ReservationDTO;
import com.app.campapp.model.Reservation;
import com.app.campapp.service.impl.ReservationServiceImpl;
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
@RequestMapping(value = "/reservation", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationController {

    @Autowired
    private ReservationServiceImpl reservationServiceImpl;

    @PreAuthorize("hasRole('ROLE_CAMPER')")
    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody ReservationDTO reservationDTO) {
        if (reservationServiceImpl.createReservation(reservationDTO)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_CATERER')")
    @PutMapping(value = "/{id}/accept")
    public ResponseEntity<?> acceptReservation(@PathVariable Long id) {
        if (reservationServiceImpl.acceptReservation(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CATERER')")
    @PutMapping(value = "/{id}/reject")
    public ResponseEntity<?> rejectReservation(@PathVariable Long id) {
        if (reservationServiceImpl.rejectReservation(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_CAMPER') or hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}/cancel")
    public ResponseEntity<?> cancelReservation(@PathVariable Long id) {
        if (reservationServiceImpl.cancelReservation(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasRole('ROLE_CATERER')")
    @GetMapping(value = "/pending/caterer/{id}")
    public ResponseEntity<List<ReservationDTO>> getPendingReservationsForCaterer(@PathVariable Long id) {
        List<Reservation> reservations = reservationServiceImpl.getPendingReservationsForCaterer(id);
        List<ReservationDTO> reservationDTOS = new ArrayList<>();

        for (Reservation r : reservations) {
            reservationDTOS.add(new ReservationDTO(r));
        }
        return new ResponseEntity<>(reservationDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_CATERER')")
    @GetMapping(value = "/caterer/{id}")
    public ResponseEntity<List<ReservationDTO>> getAllReservationsForCaterer(@PathVariable Long id) {
        List<Reservation> reservations = reservationServiceImpl.getAllReservationForCaterer(id);
        List<ReservationDTO> reservationDTOS = new ArrayList<>();

        for (Reservation r : reservations) {
            reservationDTOS.add(new ReservationDTO(r));
        }
        return new ResponseEntity<>(reservationDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_CATERER')")
    @GetMapping(value = "/pending/notification/caterer/{id}")
    public ResponseEntity<?> getNumberOfPendingReservations(@PathVariable Long id) {
        return new ResponseEntity<>(reservationServiceImpl.numberOfPendingReservations(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_CAMPER')")
    @GetMapping(value = "/pending/camper/{id}")
    public ResponseEntity<List<ReservationDTO>> getPendingReservationsForCamper(@PathVariable Long id) {
        List<Reservation> reservations = reservationServiceImpl.getPendingReservationsForCamper(id);
        List<ReservationDTO> reservationDTOS = new ArrayList<>();

        for (Reservation r : reservations) {
            reservationDTOS.add(new ReservationDTO(r));
        }
        return new ResponseEntity<>(reservationDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_CAMPER')")
    @GetMapping(value = "/accepted/camper/{id}")
    public ResponseEntity<List<ReservationDTO>> getAcceptedReservationsForCamper(@PathVariable Long id) {
        List<Reservation> reservations = reservationServiceImpl.getAcceptedReservationsForCamper(id);
        List<ReservationDTO> reservationDTOS = new ArrayList<>();

        for (Reservation r : reservations) {
            reservationDTOS.add(new ReservationDTO(r));
        }
        return new ResponseEntity<>(reservationDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_CAMPER')")
    @GetMapping(value = "/camper/{id}")
    public ResponseEntity<List<ReservationDTO>> getAllReservationsForCamper(@PathVariable Long id) {
        List<Reservation> reservations = reservationServiceImpl.getAllReservationsForCamper(id);
        List<ReservationDTO> reservationDTOS = new ArrayList<>();

        for (Reservation r : reservations) {
            reservationDTOS.add(new ReservationDTO(r));
        }
        return new ResponseEntity<>(reservationDTOS, HttpStatus.OK);
    }
}
