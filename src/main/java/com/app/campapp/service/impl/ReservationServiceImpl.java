package com.app.campapp.service.impl;

import com.app.campapp.enums.ReservationType;
import com.app.campapp.model.Reservation;
import com.app.campapp.repository.ReservationRepository;
import com.app.campapp.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CamperCanRateServiceImpl camperCanRateServiceImpl;

    @Autowired
    private CamperCanReportServiceImpl camperCanReportServiceImpl;

    //posalti mejl da je rezervacija prihvacena
    @Override
    public boolean acceptReservation(Long id) {
        Reservation reservation = reservationRepository.getOne(id);

        if (reservation != null) {
            reservation.setReservationType(ReservationType.ACCEPTED);
            reservationRepository.save(reservation);
            //omoguciti kamperu da moze da oceni mesto za kampovanje nakon isteka rezervacije
            camperCanRateServiceImpl.createCamperCanRate(reservation.getCamper().getId(),
                    reservation.getCampsite().getId(), reservation.getEndDate());
            //omoguciti kamperu da moze da prijavi vlasnika mesta za kampovanje nakon isteka rezervacije ukoliko to zeli
            camperCanReportServiceImpl.createCamperCanReport(reservation.getCamper().getId(),
                    reservation.getCampsite().getCaterer().getId(), reservation.getEndDate());
            return true;
        }
        return false;
    }

    //poslati mejl da je rezervacija odbijena
    @Override
    public boolean rejectReservation(Long id) {
        Reservation reservation = reservationRepository.getOne(id);

        if (reservation != null) {
            reservation.setReservationType(ReservationType.REJECTED);
            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }

    @Override
    public boolean cancelReservation(Long id) {
        Reservation reservation = reservationRepository.getOne(id);

        if (reservation != null) {
            reservation.setReservationType(ReservationType.CANCELED);
            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }
}
