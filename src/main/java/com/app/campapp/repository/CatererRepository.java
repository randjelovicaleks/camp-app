package com.app.campapp.repository;

import com.app.campapp.model.Caterer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CatererRepository extends JpaRepository<Caterer, Long> {

    @Query(value = "SELECT * FROM caterer WHERE number_of_reports >= 3 AND user_status = 'ACTIVE'", nativeQuery = true)
    List<Caterer> findAllCaterersWithReports();
}
