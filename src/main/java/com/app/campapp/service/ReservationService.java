package com.app.campapp.service;

public interface ReservationService {

    boolean acceptReservation(Long id);
    boolean rejectReservation(Long id);
    boolean cancelReservation(Long id);
}
