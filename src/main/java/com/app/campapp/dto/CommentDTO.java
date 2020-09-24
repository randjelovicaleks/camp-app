package com.app.campapp.dto;

import com.app.campapp.model.Comment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

public class CommentDTO {

    private Long id;
    @NotBlank(message="Content must not be empty.")
    @Pattern(regexp="^[a-zA-Z0-9.,?! ]*$",
            message="Content must contain only letters, digits and punctuation marks.")
    private String content;
    private LocalDate date;
    private CamperDTO camperDTO;
    private CampsiteDTO campsiteDTO;
    private ReplyDTO replyDTO;

    public CommentDTO() {
    }

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.date = comment.getDate();
        this.camperDTO = new CamperDTO(comment.getCamper());
        this.campsiteDTO = new CampsiteDTO(comment.getCampsite());
        if (comment.getReply() != null) {
            this.replyDTO = new ReplyDTO(comment.getReply());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public CamperDTO getCamperDTO() {
        return camperDTO;
    }

    public void setCamperDTO(CamperDTO camperDTO) {
        this.camperDTO = camperDTO;
    }

    public CampsiteDTO getCampsiteDTO() {
        return campsiteDTO;
    }

    public void setCampsiteDTO(CampsiteDTO campsiteDTO) {
        this.campsiteDTO = campsiteDTO;
    }

    public ReplyDTO getReplyDTO() {
        return replyDTO;
    }

    public void setReplyDTO(ReplyDTO replyDTO) {
        this.replyDTO = replyDTO;
    }
}
