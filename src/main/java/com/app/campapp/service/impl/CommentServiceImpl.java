package com.app.campapp.service.impl;

import com.app.campapp.dto.CommentDTO;
import com.app.campapp.model.Camper;
import com.app.campapp.model.Campsite;
import com.app.campapp.model.Comment;
import com.app.campapp.model.Reply;
import com.app.campapp.repository.CamperRepository;
import com.app.campapp.repository.CampsiteRepository;
import com.app.campapp.repository.CommentRepository;
import com.app.campapp.repository.ReplyRepository;
import com.app.campapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CampsiteRepository campsiteRepository;

    @Autowired
    private CamperRepository camperRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private CamperCanCommentServiceImpl camperCanCommentServiceImpl;

    @Override
    public List<CommentDTO> getAllCommentsAndRepliesForCampsite(Long idCampsite) {
        Campsite campsite = campsiteRepository.getOne(idCampsite);
        List<CommentDTO> commentDTOS = new ArrayList<>();

        for (Comment c : campsite.getComments()) {
            commentDTOS.add(new CommentDTO(c));
        }

        return commentDTOS;
    }

    @Override
    public boolean createComment(CommentDTO commentDTO) {
        Campsite campsite = campsiteRepository.getOne(commentDTO.getCampsiteDTO().getId());
        Camper camper = camperRepository.getOne(commentDTO.getCamperDTO().getId());

        if (campsite != null && camper != null) {
            Comment comment = new Comment();
            comment.setContent(commentDTO.getContent());
            comment.setDate(LocalDate.now());
            comment.setCamper(camper);
            comment.setCampsite(campsite);
            //promeniti da je kamper komentarisao mesto
            if (camperCanCommentServiceImpl.changeCamperCanComment(camper.getId(), campsite.getId())) {
                commentRepository.save(comment);
                return true;
            }
        }
        return false;
    }
}
