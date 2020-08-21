package com.app.campapp.service;

import com.app.campapp.dto.CommentDTO;
import com.app.campapp.model.Comment;
import java.util.List;

public interface CommentService {

    List<CommentDTO> getAllCommentsAndRepliesForCampsite(Long idCampsite);
    boolean createComment(CommentDTO commentDTO);
}
