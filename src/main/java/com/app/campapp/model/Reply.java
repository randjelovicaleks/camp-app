package com.app.campapp.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "posting_date", nullable = false)
    private LocalDate date;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Caterer caterer;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Campsite campsite;

    public Reply() {
    }

    public Reply(Long id, String content, LocalDate date, Caterer caterer, Campsite campsite) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.caterer = caterer;
        this.campsite = campsite;
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

    public Caterer getCaterer() {
        return caterer;
    }

    public void setCaterer(Caterer caterer) {
        this.caterer = caterer;
    }

    public Campsite getCampsite() {
        return campsite;
    }

    public void setCampsite(Campsite campsite) {
        this.campsite = campsite;
    }
}
