package com.app.campapp.model;

import com.app.campapp.enums.ReservationType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Camper camper;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Campsite campsite;

    @Enumerated(EnumType.STRING)
    private ReservationType reservationType;

    public Reservation() {
    }

    public Reservation(Long id, LocalDate startDate, LocalDate endDate, Camper camper, Campsite campsite, ReservationType reservationType) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.camper = camper;
        this.campsite = campsite;
        this.reservationType = reservationType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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

    public ReservationType getReservationType() {
        return reservationType;
    }

    public void setReservationType(ReservationType reservationType) {
        this.reservationType = reservationType;
    }
}
