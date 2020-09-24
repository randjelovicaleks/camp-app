package com.app.campapp.service.impl;

import com.app.campapp.dto.CatererDTO;
import com.app.campapp.enums.ReservationType;
import com.app.campapp.enums.UserStatus;
import com.app.campapp.model.Campsite;
import com.app.campapp.model.Caterer;
import com.app.campapp.model.Reservation;
import com.app.campapp.repository.CampsiteRepository;
import com.app.campapp.repository.CatererRepository;
import com.app.campapp.repository.ReservationRepository;
import com.app.campapp.service.CatererService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CatererServiceImpl implements CatererService {

    @Autowired
    private CatererRepository catererRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CampsiteRepository campsiteRepository;

    @Autowired
    private CamperCanReportServiceImpl camperCanReportServiceImpl;

    @Autowired
    private ReservationServiceImpl reservationServiceImpl;

    @Override
    public boolean reportCaterer(Long catererId, Long camperId) {
        Caterer caterer = catererRepository.getOne(catererId);

        if (caterer != null) {
            int numberOfReports = caterer.getNumberOfReports();
            numberOfReports++;
            caterer.setNumberOfReports(numberOfReports);
            if (camperCanReportServiceImpl.changeCamperCanReport(camperId, catererId)) {
                catererRepository.save(caterer);
                return true;
            }
        }
        return false;
    }

    //kada se blokira ugostitelj ne treba prikazivati u pretrazi sva njegova mesta za kampovanje
    @Override
    public boolean blockCaterer(Long id) {
        Caterer caterer = catererRepository.getOne(id);
        List<Reservation> pendingReservations = reservationServiceImpl.getPendingReservationsForCaterer(id);

        if (caterer != null) {
            if (!pendingReservations.isEmpty()) {
                for (Reservation r : pendingReservations) {
                    r.setReservationType(ReservationType.REJECTED);
                    reservationRepository.save(r);
                }
            }

            for (Campsite c : new ArrayList<>(caterer.getCampsites())) {
                c.setVisible(false);
                campsiteRepository.save(c);
            }

            caterer.setUserStatus(UserStatus.BLOCKED);
            caterer.setNumberOfReports(0);
            caterer.setEnabled(false);
            catererRepository.save(caterer);
            return true;
        }
        return false;
    }

    @Override
    public Caterer getCaterer(Long id) {
        return catererRepository.getOne(id);
    }

    @Override
    public List<Caterer> getAllCaterers() {
        return catererRepository.findAll();
    }

    @Override
    public List<Caterer> getCaterersWithReports() {
        List<Caterer> caterers = catererRepository.findAll();
        List<Caterer> catererWithReports = new ArrayList<>();

        for (Caterer c : caterers) {
            if (c.getNumberOfReports() >= 3) {
                catererWithReports.add(c);
            }
        }
        return catererWithReports;
    }

    @Override
    public int reportedCaterers() {
        List<Caterer> caterers = getCaterersWithReports();
        return caterers.size();
    }

}
