package com.app.campapp.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class CamperCanComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="camper_id")
    private Long camperId;

    @Column(name="campsite_id")
    private Long campsiteId;

    @Column(name="commented")
    private boolean commented;

    @Column(name="reservation_end_date")
    private LocalDate reservationEndDate;

    public CamperCanComment() {
    }

    public CamperCanComment(Long id, Long camperId, Long campsiteId, boolean commented, LocalDate reservationEndDate) {
        this.id = id;
        this.camperId = camperId;
        this.campsiteId = campsiteId;
        this.commented = commented;
        this.reservationEndDate = reservationEndDate;
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

    public boolean isCommented() {
        return commented;
    }

    public void setCommented(boolean commented) {
        this.commented = commented;
    }

    public LocalDate getReservationEndDate() {
        return reservationEndDate;
    }

    public void setReservationEndDate(LocalDate reservationEndDate) {
        this.reservationEndDate = reservationEndDate;
    }
}
