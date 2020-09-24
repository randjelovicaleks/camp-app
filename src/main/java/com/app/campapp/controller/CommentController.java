package com.app.campapp.controller;

import com.app.campapp.dto.CommentDTO;
import com.app.campapp.model.Comment;
import com.app.campapp.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/comment", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController {

    @Autowired
    private CommentServiceImpl commentServiceImpl;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CAMPER') or hasRole('ROLE_CATERER')")
    @GetMapping(value = "/campsite/{idCampsite}")
    public ResponseEntity<List<CommentDTO>> getAllCommentsAndRepliesForCampsite(@PathVariable Long idCampsite) {
        return new ResponseEntity<>(commentServiceImpl.getAllCommentsAndRepliesForCampsite(idCampsite), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_CAMPER')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createComment(@Valid @RequestBody CommentDTO commentDTO) {
        if (commentServiceImpl.createComment(commentDTO)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
