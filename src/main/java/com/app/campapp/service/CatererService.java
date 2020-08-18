package com.app.campapp.service;

import com.app.campapp.dto.CatererDTO;

import java.util.List;

public interface CatererService {

    boolean reportCaterer(Long id);
    List<CatererDTO> findAllCaterersWithReports();
}
