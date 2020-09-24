package com.app.campapp.service.impl;

import com.app.campapp.model.CamperCanComment;
import com.app.campapp.model.CamperCanRate;
import com.app.campapp.model.Campsite;
import com.app.campapp.repository.CamperCanCommentRepository;
import com.app.campapp.repository.CampsiteRepository;
import com.app.campapp.service.CamperCanCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CamperCanCommentServiceImpl implements CamperCanCommentService {

    @Autowired
    private CamperCanCommentRepository camperCanCommentRepository;

    @Autowired
    private CampsiteRepository campsiteRepository;

    @Override
    public boolean camperCanComment(Long camperId, Long campsiteId) {
        Campsite campsite = campsiteRepository.getOne(campsiteId);

        if (campsite != null) {
            LocalDate today = LocalDate.now();
            CamperCanComment camperCanComment = camperCanCommentRepository.findCampersCanComment(camperId, campsiteId, false, today);
            if(camperCanComment != null) {
                return true;
            }
        }
        return false;
    }

    public boolean changeCamperCanComment(Long camperId, Long campsiteId) {
        LocalDate today = LocalDate.now();
        CamperCanComment camperCanComment = camperCanCommentRepository.findCampersCanComment(camperId, campsiteId, false, today);
        if (camperCanComment != null) {
            camperCanComment.setCommented(true);
            camperCanCommentRepository.save(camperCanComment);
            return true;
        }
        return false;
    }

    public boolean createCamperCanComment(Long camperId, Long campsiteId, LocalDate reservationEndDate) {
        Campsite campsite = campsiteRepository.getOne(campsiteId);

        if (campsite != null) {
            CamperCanComment camperCanComment = new CamperCanComment();
            camperCanComment.setCamperId(camperId);
            camperCanComment.setCampsiteId(campsiteId);
            camperCanComment.setCommented(false);
            camperCanComment.setReservationEndDate(reservationEndDate);
            camperCanCommentRepository.save(camperCanComment);
            return true;
        }
        return false;
    }
}
