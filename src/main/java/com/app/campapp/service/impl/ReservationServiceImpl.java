package com.app.campapp.service.impl;

import com.app.campapp.dto.ReservationDTO;
import com.app.campapp.enums.ReservationType;
import com.app.campapp.model.Camper;
import com.app.campapp.model.Campsite;
import com.app.campapp.model.Reservation;
import com.app.campapp.repository.CamperRepository;
import com.app.campapp.repository.CampsiteRepository;
import com.app.campapp.repository.ReservationRepository;
import com.app.campapp.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CamperCanCommentServiceImpl camperCanCommentServiceImpl;

    @Autowired
    private CamperCanRateServiceImpl camperCanRateServiceImpl;

    @Autowired
    private CamperCanReportServiceImpl camperCanReportServiceImpl;

    @Autowired
    private CampsiteRepository campsiteRepository;

    @Autowired
    private CamperRepository camperRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public boolean createReservation(ReservationDTO reservationDTO) {
        Camper camper = camperRepository.getOne(reservationDTO.getCamperDTO().getId());
        Campsite campsite = campsiteRepository.getOne(reservationDTO.getCampsiteDTO().getId());
        List<Reservation> reservations = reservationRepository.findReservations(campsite.getId());

        if (camper != null && campsite != null) {
            int counter = 0;
            for (Reservation r : reservations) {
                if (r.getStartDate().isEqual(reservationDTO.getStartDate()) && r.getEndDate().isEqual(reservationDTO.getEndDate())) {
                    counter++;
                } else if (r.getStartDate().isBefore(reservationDTO.getStartDate()) && r.getEndDate().isAfter(reservationDTO.getEndDate())) {
                    counter++;
                } else if (r.getStartDate().isEqual(reservationDTO.getStartDate()) && r.getEndDate().isAfter(reservationDTO.getEndDate())) {
                    counter++;
                } else if (r.getStartDate().isBefore(reservationDTO.getStartDate()) && r.getEndDate().isAfter(reservationDTO.getStartDate())) {
                    counter++;
                } else if (r.getStartDate().isBefore(reservationDTO.getEndDate()) && r.getEndDate().isAfter(reservationDTO.getEndDate())) {
                    counter++;
                }
            }
            if (campsite.getTentSpotsNumber() > counter) {
                Reservation reservation = new Reservation();
                reservation.setStartDate(reservationDTO.getStartDate());
                reservation.setEndDate(reservationDTO.getEndDate());
                reservation.setCamper(camper);
                reservation.setCampsite(campsite);
                reservation.setReservationType(ReservationType.PENDING);
                reservationRepository.save(reservation);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean acceptReservation(Long id) {
        Reservation reservation = reservationRepository.getOne(id);

        if (reservation != null) {
            reservation.setReservationType(ReservationType.ACCEPTED);
            reservationRepository.save(reservation);
            //omoguciti kamperu da moze da komentarise mesto za kampovanje nakon isteka rezervacije
            camperCanCommentServiceImpl.createCamperCanComment(reservation.getCamper().getId(),
                    reservation.getCampsite().getId(), reservation.getEndDate());
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

    @Override
    public boolean rejectReservation(Long id) {
        Reservation reservation = reservationRepository.getOne(id);

        if (reservation != null) {
            reservation.setReservationType(ReservationType.REJECTED);
            reservationRepository.save(reservation);
            try {
                emailService.sendEmailForRejectingReservation(reservation);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

    @Override
    public List<Reservation> getPendingReservationsForCaterer(Long id) {
        List<Reservation> reservations = reservationRepository.findByCampsite_Caterer_Id(id);
        List<Reservation> pendingReservations = new ArrayList<>();

        for (Reservation r : reservations) {
            if (r.getReservationType().equals(ReservationType.PENDING)) {
                pendingReservations.add(r);
            }
        }
        return pendingReservations;
    }

    @Override
    public List<Reservation> getAllReservationForCaterer(Long id) {
        List<Reservation> allReservations = reservationRepository.findByCampsite_Caterer_Id(id);
        List<Reservation> filteredReservations = new ArrayList<>();

        for (Reservation r : allReservations) {
            if (!r.getReservationType().equals(ReservationType.PENDING)) {
                filteredReservations.add(r);
            }
        }
        return filteredReservations;
    }

    @Override
    public int numberOfPendingReservations(Long id) {
        List<Reservation> pendingReservations = getPendingReservationsForCaterer(id);
        return pendingReservations.size();
    }

    @Override
    public List<Reservation> getPendingReservationsForCamper(Long id) {
        return reservationRepository.findPendingReservationsForCamper(id);
    }

    @Override
    public List<Reservation> getAcceptedReservationsForCamper(Long id) {
        return reservationRepository.findAcceptedReservationsForCamper(id);
    }

    @Override
    public List<Reservation> getAllReservationsForCamper(Long id) {
        return reservationRepository.findAllReservationsForCamper(id);
    }
}
