package com.app.campapp.service;

import com.app.campapp.model.Caterer;
import java.util.List;

public interface CatererService {

    boolean reportCaterer(Long catererId, Long camperId);
    boolean blockCaterer(Long id);
    Caterer getCaterer(Long id);
    List<Caterer> getAllCaterers();
    List<Caterer> getCaterersWithReports();
    int reportedCaterers();
}
