package com.app.campapp.controller;

import com.app.campapp.dto.ReplyDTO;
import com.app.campapp.service.impl.ReplyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/reply", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReplyController {

    @Autowired
    private ReplyServiceImpl replyServiceImpl;

    @PreAuthorize("hasRole('ROLE_CATERER')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createReply(@PathVariable Long idCampsite, @RequestBody ReplyDTO replyDTO) {
        if (replyServiceImpl.createReply(idCampsite, replyDTO)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
