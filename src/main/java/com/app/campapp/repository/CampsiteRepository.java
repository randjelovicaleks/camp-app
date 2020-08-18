package com.app.campapp.repository;

import com.app.campapp.model.Campsite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;

public interface CampsiteRepository extends JpaRepository<Campsite, Long> {

    @Query(value = "select * from campsite where opening_date <= ?1 and closing_date >= ?2 and lower(nearest_city) like lower(?3)", nativeQuery = true)
    List<Campsite> simpleSerach(LocalDate dateFrom, LocalDate dateTo, String nearestCity);

    @Query(value = "select * from campsite order by rating desc limit 3", nativeQuery = true)
    List<Campsite> top3BestRated();
}
