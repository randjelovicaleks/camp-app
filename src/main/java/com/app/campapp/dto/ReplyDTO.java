package com.app.campapp.dto;

import com.app.campapp.model.Reply;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

public class ReplyDTO {

    private Long id;
    @NotBlank(message="Content must not be empty.")
    @Pattern(regexp="^[a-zA-Z0-9.,?! ]*$",
            message="Content must contain only letters, digits and punctuation marks.")
    private String content;
    private LocalDate date;
    private CatererDTO catererDTO;
    private CampsiteDTO campsiteDTO;

    public ReplyDTO() {
    }

    public ReplyDTO(Reply reply) {
        this.id = reply.getId();
        this.content = reply.getContent();
        this.date = reply.getDate();
        this.catererDTO = new CatererDTO(reply.getCaterer());
        this.campsiteDTO = new CampsiteDTO(reply.getCampsite());
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

    public CatererDTO getCatererDTO() {
        return catererDTO;
    }

    public void setCatererDTO(CatererDTO catererDTO) {
        this.catererDTO = catererDTO;
    }

    public CampsiteDTO getCampsiteDTO() {
        return campsiteDTO;
    }

    public void setCampsiteDTO(CampsiteDTO campsiteDTO) {
        this.campsiteDTO = campsiteDTO;
    }
}
