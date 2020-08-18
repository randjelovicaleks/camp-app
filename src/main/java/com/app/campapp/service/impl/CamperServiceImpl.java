package com.app.campapp.service.impl;

import com.app.campapp.enums.UserStatus;
import com.app.campapp.model.Camper;
import com.app.campapp.model.Reservation;
import com.app.campapp.repository.CamperRepository;
import com.app.campapp.repository.ReservationRepository;
import com.app.campapp.service.CamperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CamperServiceImpl implements CamperService {

    @Autowired
    private CamperRepository camperRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    //ako kamper ima rezervacije koje su pending ili koje traju ne moze da se blokira
    @Override
    public boolean blockCamper(Long id) {
        Camper camper = camperRepository.getOne(id);
        List<Reservation> reservations = reservationRepository.findPendingReservations();

        if (camper != null) {
            if (reservations.isEmpty()) {
                camper.setUserStatus(UserStatus.BLOCKED);
                camper.setEnabled(false);
                camperRepository.save(camper);
                return true;
            }
        }
        return false;
    }
}
