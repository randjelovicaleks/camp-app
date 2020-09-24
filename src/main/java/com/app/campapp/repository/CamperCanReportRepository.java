package com.app.campapp.repository;

import com.app.campapp.model.CamperCanRate;
import com.app.campapp.model.CamperCanReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;

public interface CamperCanReportRepository extends JpaRepository<CamperCanReport, Long> {

    @Query(value = "SELECT * FROM camper_can_report WHERE camper_id = ?1 AND caterer_id = ?2 AND reported = ?3 AND reservation_end_date <= ?4", nativeQuery = true)
    CamperCanReport findCamperCanReport(Long camperId, Long catererId, boolean reported, LocalDate today);
}
