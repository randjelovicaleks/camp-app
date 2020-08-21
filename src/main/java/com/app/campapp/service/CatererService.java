package com.app.campapp.service;

import com.app.campapp.dto.CatererDTO;
import com.app.campapp.model.Caterer;

import java.util.List;

public interface CatererService {

    boolean reportCaterer(Long id);
    List<CatererDTO> findAllCaterersWithReports();
    boolean blockCaterer(Long id);
    Caterer getCaterer(Long id);
}
