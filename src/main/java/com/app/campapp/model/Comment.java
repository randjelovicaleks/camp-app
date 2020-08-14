package com.app.campapp.model;

import com.app.campapp.enums.CommentType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CommentType commentType;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "posting_date", nullable = false)
    private LocalDateTime date;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Caterer caterer;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Camper camper;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Campsite campsite;

    public Comment() {
    }

    public Comment(Long id, CommentType commentType, String content, LocalDateTime date, Caterer caterer,
                   Camper camper, Campsite campsite) {
        this.id = id;
        this.commentType = commentType;
        this.content = content;
        this.date = date;
        this.caterer = caterer;
        this.camper = camper;
        this.campsite = campsite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CommentType getCommentType() {
        return commentType;
    }

    public void setCommentType(CommentType commentType) {
        this.commentType = commentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Caterer getCaterer() {
        return caterer;
    }

    public void setCaterer(Caterer caterer) {
        this.caterer = caterer;
    }

    public Camper getCamper() {
        return camper;
    }

    public void setCamper(Camper camper) {
        this.camper = camper;
    }

    public Campsite getCampsite() {
        return campsite;
    }

    public void setCampsite(Campsite campsite) {
        this.campsite = campsite;
    }
}
