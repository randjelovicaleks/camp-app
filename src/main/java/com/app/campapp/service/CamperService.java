package com.app.campapp.service;

public interface CamperService {

    boolean blockCamper(Long id);
    boolean blockCamperWhoCanceledReservation(Long id);
    //List<CamperDTO> findCampersWhoCanceledReservations();

}
