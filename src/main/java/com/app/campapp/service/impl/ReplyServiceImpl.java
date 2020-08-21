package com.app.campapp.service.impl;

import com.app.campapp.dto.ReplyDTO;
import com.app.campapp.model.Campsite;
import com.app.campapp.model.Caterer;
import com.app.campapp.model.Comment;
import com.app.campapp.model.Reply;
import com.app.campapp.repository.CampsiteRepository;
import com.app.campapp.repository.CatererRepository;
import com.app.campapp.repository.CommentRepository;
import com.app.campapp.repository.ReplyRepository;
import com.app.campapp.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CatererRepository catererRepository;

    @Autowired
    private CampsiteRepository campsiteRepository;

    @Override
    public boolean createReply(Long idComment, ReplyDTO replyDTO) {
        Comment comment = commentRepository.getOne(idComment);
        Caterer caterer = catererRepository.getOne(replyDTO.getCatererDTO().getId());
        Campsite campsite = campsiteRepository.getOne(replyDTO.getCampsiteDTO().getId());

        if (comment != null) {
            Reply reply = new Reply();
            reply.setContent(replyDTO.getContent());
            reply.setDate(replyDTO.getDate());
            reply.setCaterer(caterer);
            reply.setCampsite(campsite);
            comment.setReply(reply);
            commentRepository.save(comment);
            replyRepository.save(reply);
            return true;
        }

        return false;
    }
}
