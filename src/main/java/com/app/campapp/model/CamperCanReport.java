package com.app.campapp.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class CamperCanReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="camper_id")
    private Long camperId;

    @Column(name="campsite_id")
    private Long catererId;

    @Column(name="reservation_end_date")
    private LocalDate reservationEndDate;

    public CamperCanReport() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCamperId() {
        return camperId;
    }

    public void setCamperId(Long camperId) {
        this.camperId = camperId;
    }

    public Long getCatererId() {
        return catererId;
    }

    public void setCatererId(Long catererId) {
        this.catererId = catererId;
    }

    public LocalDate getReservationEndDate() {
        return reservationEndDate;
    }

    public void setReservationEndDate(LocalDate reservationEndDate) {
        this.reservationEndDate = reservationEndDate;
    }
}
