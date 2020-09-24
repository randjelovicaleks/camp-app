package com.app.campapp.dto;

import com.app.campapp.enums.ReservationType;
import com.app.campapp.model.Reservation;
import java.time.LocalDate;

public class ReservationDTO {

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private CamperDTO camperDTO;
    private CampsiteDTO campsiteDTO;
    private ReservationType reservationType;

    public ReservationDTO() {
    }

    public ReservationDTO(Reservation reservation) {
        this.id = reservation.getId();
        this.startDate = reservation.getStartDate();
        this.endDate = reservation.getEndDate();
        this.camperDTO = new CamperDTO(reservation.getCamper());
        this.campsiteDTO = new CampsiteDTO(reservation.getCampsite());
        this.reservationType = reservation.getReservationType();
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

    public ReservationType getReservationType() {
        return reservationType;
    }

    public void setReservationType(ReservationType reservationType) {
        this.reservationType = reservationType;
    }
}
