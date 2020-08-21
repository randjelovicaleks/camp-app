package com.app.campapp.repository;

import com.app.campapp.model.CamperCanComment;
import com.app.campapp.model.CamperCanRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;

public interface CamperCanCommentRepository extends JpaRepository<CamperCanComment, Long> {

    @Query(value = "SELECT * FROM camper_can_comment WHERE camper_id = ?1 AND campsite_id = ?2 AND commented = ?3 and reservation_end_date <= ?4"  , nativeQuery = true)
    CamperCanComment findCampersCanComment(Long camperId, Long campsiteId, boolean alreadyCommented, LocalDate today);
}
