package com.app.campapp.repository;

import com.app.campapp.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(value = "SELECT * FROM reservation WHERE reservation_type = 'PENDING'", nativeQuery = true)
    List<Reservation> findPendingReservations();

    @Query(value = "SELECT * FROM reservation WHERE reservation_type = 'CANCELED' AND camper_id = ?1", nativeQuery = true)
    List<Reservation> findCanceledReservations(Long id);

    @Query(value = "SELECT * FROM reservation WHERE reservation_type = 'CANCELED'", nativeQuery = true)
    List<Reservation> findCanceledReservations();
}
