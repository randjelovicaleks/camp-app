package com.app.campapp.service;

import com.app.campapp.dto.ReservationDTO;
import com.app.campapp.model.Reservation;

import java.util.List;

public interface ReservationService {

    boolean createReservation(ReservationDTO reservationDTO);
    boolean acceptReservation(Long id);
    boolean rejectReservation(Long id);
    boolean cancelReservation(Long id);
    List<Reservation> getPendingReservationsForCaterer(Long id);
    List<Reservation> getAllReservationForCaterer(Long id);
    int numberOfPendingReservations(Long id);
    List<Reservation> getPendingReservationsForCamper(Long id);
    List<Reservation> getAcceptedReservationsForCamper(Long id);
    List<Reservation> getAllReservationsForCamper(Long id);
}
