package com.app.campapp.repository;

import com.app.campapp.model.CamperCanRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;

public interface CamperCanRateRepository extends JpaRepository<CamperCanRate, Long> {

    @Query(value = "SELECT * FROM camper_can_rate WHERE camper_id = ?1 AND campsite_id = ?2 AND rated = ?3 and reservation_end_date <= ?4"  , nativeQuery = true)
    CamperCanRate findCampersCanRate(Long camperId, Long campsiteId, boolean alreadyRated, LocalDate today);
}
