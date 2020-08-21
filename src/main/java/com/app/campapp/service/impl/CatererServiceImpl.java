package com.app.campapp.service.impl;

import com.app.campapp.dto.CatererDTO;
import com.app.campapp.enums.UserStatus;
import com.app.campapp.model.Caterer;
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

    @Override
    public boolean reportCaterer(Long id) {
        Caterer caterer = catererRepository.getOne(id);

        if (caterer != null) {
            int numberOfReports = caterer.getNumberOfReports();
            numberOfReports++;
            return true;
        }
        return false;
    }

    @Override
    public List<CatererDTO> findAllCaterersWithReports() {
        List<Caterer> caterers = catererRepository.findAllCaterersWithReports();
        List<CatererDTO> catererDTOS = new ArrayList<>();

        for (Caterer c : caterers) {
            catererDTOS.add(new CatererDTO(c));
        }

        return catererDTOS;
    }

    //odraditi provere za rezervacije
    @Override
    public boolean blockCaterer(Long id) {
        Caterer caterer = catererRepository.getOne(id);

        if (caterer != null) {
            caterer.setUserStatus(UserStatus.BLOCKED);
            catererRepository.save(caterer);
            return true;
        }
        return false;
    }

    @Override
    public Caterer getCaterer(Long id) {
        return catererRepository.getOne(id);
    }

}
