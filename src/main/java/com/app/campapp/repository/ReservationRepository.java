package com.app.campapp.repository;

import com.app.campapp.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(value = "SELECT * FROM reservation WHERE reservation_type = 'PENDING'", nativeQuery = true)
    List<Reservation> findPendingReservations();

    @Query(value = "SELECT * FROM reservation WHERE reservation_type = 'CANCELED' AND camper_id = ?1", nativeQuery = true)
    List<Reservation> findCanceledReservationsForCamper(Long id);

    @Query(value = "SELECT * FROM reservation WHERE reservation_type = 'CANCELED'", nativeQuery = true)
    List<Reservation> findCanceledReservations();

    @Query(value = "SELECT * FROM reservation WHERE reservation_type IN ('ACCEPTED', 'PENDING') AND campsite_id = ?1", nativeQuery = true)
    List<Reservation> findReservations(Long idCampsite);

    @Query(value = "SELECT * FROM reservation WHERE reservation_type = 'PENDING' AND camper_id = ?1", nativeQuery = true)
    List<Reservation> findPendingReservationsForCamper(Long id);

    @Query(value = "SELECT * FROM reservation WHERE reservation_type = 'ACCEPTED' AND camper_id = ?1", nativeQuery = true)
    List<Reservation> findAcceptedReservationsForCamper(Long id);

    List<Reservation> findByCampsite_Caterer_Id(Long id);

    @Query(value = "SELECT * FROM reservation WHERE reservation_type IN ('ACCEPTED', 'CANCELED', 'REJECTED') AND camper_id = ?1", nativeQuery = true)
    List<Reservation> findAllReservationsForCamper(Long id);

}
