package com.app.campapp.service;

import com.app.campapp.dto.ReplyDTO;

public interface ReplyService {

    boolean createReply(Long idComment, ReplyDTO replyDTO);
}
