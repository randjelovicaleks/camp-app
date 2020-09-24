package com.app.campapp.service.impl;

import com.app.campapp.model.CamperCanRate;
import com.app.campapp.model.Campsite;
import com.app.campapp.repository.CamperCanRateRepository;
import com.app.campapp.repository.CampsiteRepository;
import com.app.campapp.service.CamperCanRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class CamperCanRateServiceImpl implements CamperCanRateService {

    @Autowired
    private CamperCanRateRepository camperCanRateRepository;

    @Autowired
    private CampsiteRepository campsiteRepository;

    @Override
    public boolean camperCanRate(Long camperId, Long campsiteId) {
        Campsite campsite = campsiteRepository.getOne(campsiteId);

        if (campsite != null) {
            LocalDate today = LocalDate.now();
            CamperCanRate camperCanRate = camperCanRateRepository.findCamperCanRate(camperId, campsiteId, false, today);

            if(camperCanRate != null) {
                return true;
            }
        }
        return false;
    }

    public boolean changeCamperCanRate(Long camperId, Long campsiteId) {
        LocalDate today = LocalDate.now();
        CamperCanRate camperCanRate = camperCanRateRepository.findCamperCanRate(camperId, campsiteId, false, today);

        if(camperCanRate != null) {
            camperCanRate.setRated(true);
            camperCanRateRepository.save(camperCanRate);
            return true;
        }
        return false;
    }

    public boolean createCamperCanRate(Long camperId, Long campsiteId, LocalDate reservationEndDate) {
        Campsite campsite = campsiteRepository.getOne(campsiteId);

        if (campsite != null) {
            CamperCanRate camperCanRate = new CamperCanRate();
            camperCanRate.setCamperId(camperId);
            camperCanRate.setCampsiteId(campsiteId);
            camperCanRate.setRated(false);
            camperCanRate.setReservationEndDate(reservationEndDate);
            camperCanRateRepository.save(camperCanRate);
            return true;
        }
        return false;
    }
}
