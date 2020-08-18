package com.app.campapp.repository;

import com.app.campapp.model.Campsite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;

public interface CampsiteRepository extends JpaRepository<Campsite, Long> {

    @Query(value = "SELECT * FROM campsite WHERE opening_date <= ?1 AND closing_date >= ?2 AND lower(nearest_city) LIKE lower(?3)", nativeQuery = true)
    List<Campsite> simpleSerach(LocalDate dateFrom, LocalDate dateTo, String nearestCity);

    @Query(value = "SELECT * FROM campsite ORDER BY rating DESC LIMIT 3", nativeQuery = true)
    List<Campsite> bestRatedCampsites();
}
