package com.app.campapp.service;

import com.app.campapp.model.Camper;
import java.util.List;

public interface CamperService {

    Camper getCamper(Long id);
    boolean blockCamper(Long id);
    List<Camper> findCampersWhoCanceledReservations();
    List<Camper> findAll();
    List<Camper> findActiveCampers();
    int campersWhoCanceledReservations();
    List<Camper> getFollowers(Long idFollowed);
    List<Camper> getFollowing(Long idFollower);
    List<Camper> findByNameAndSurname(String name, String surname);

}
