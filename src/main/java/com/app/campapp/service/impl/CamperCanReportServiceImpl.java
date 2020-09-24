package com.app.campapp.service.impl;

import com.app.campapp.model.CamperCanRate;
import com.app.campapp.model.CamperCanReport;
import com.app.campapp.model.Campsite;
import com.app.campapp.model.Caterer;
import com.app.campapp.repository.CamperCanReportRepository;
import com.app.campapp.repository.CatererRepository;
import com.app.campapp.service.CamperCanReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CamperCanReportServiceImpl implements CamperCanReportService {

    @Autowired
    private CamperCanReportRepository camperCanReportRepository;

    @Autowired
    private CatererRepository catererRepository;

    @Override
    public boolean camperCanReport(Long catererId, Long camperId) {
        Caterer caterer = catererRepository.getOne(catererId);

        if (caterer != null) {
            LocalDate today = LocalDate.now();
            CamperCanReport camperCanReport = camperCanReportRepository.findCamperCanReport(camperId, catererId, false,today);

            if(camperCanReport != null) {
                return true;
            }
        }
        return false;
    }

    public boolean changeCamperCanReport(Long camperId, Long catererId) {
        LocalDate today = LocalDate.now();
        CamperCanReport camperCanReport = camperCanReportRepository.findCamperCanReport(camperId, catererId, false, today);

        if(camperCanReport != null) {
            camperCanReport.setReported(true);
            camperCanReportRepository.save(camperCanReport);
            return true;
        }
        return false;
    }

    public boolean createCamperCanReport(Long camperId, Long catererId, LocalDate reservationEndDate) {
        Caterer caterer = catererRepository.getOne(catererId);

        if (caterer != null) {
            CamperCanReport camperCanReport = new CamperCanReport();
            camperCanReport.setCamperId(camperId);
            camperCanReport.setCatererId(catererId);
            camperCanReport.setReservationEndDate(reservationEndDate);
            camperCanReportRepository.save(camperCanReport);
            return true;

        }
        return false;
    }
}
