package com.app.campapp.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class CamperCanRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="camper_id")
    private Long camperId;

    @Column(name="campsite_id")
    private Long campsiteId;

    @Column(name="rated")
    private boolean rated;

    @Column(name="reservation_end_date")
    private LocalDate reservationEndDate;

    public CamperCanRate() {
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

    public Long getCampsiteId() {
        return campsiteId;
    }

    public void setCampsiteId(Long campsiteId) {
        this.campsiteId = campsiteId;
    }

    public boolean isRated() {
        return rated;
    }

    public void setRated(boolean rated) {
        this.rated = rated;
    }

    public LocalDate getReservationEndDate() {
        return reservationEndDate;
    }

    public void setReservationEndDate(LocalDate reservationEndDate) {
        this.reservationEndDate = reservationEndDate;
    }
}
