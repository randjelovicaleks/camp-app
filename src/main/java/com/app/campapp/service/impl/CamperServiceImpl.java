package com.app.campapp.service.impl;

import com.app.campapp.enums.ReservationType;
import com.app.campapp.enums.UserStatus;
import com.app.campapp.model.Camper;
import com.app.campapp.model.Reservation;
import com.app.campapp.repository.CamperRepository;
import com.app.campapp.repository.FollowingRepository;
import com.app.campapp.repository.ReservationRepository;
import com.app.campapp.service.CamperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@Service
public class CamperServiceImpl implements CamperService {

    @Autowired
    private CamperRepository camperRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private FollowingRepository followingRepository;

    @Override
    public Camper getCamper(Long id) {
        return camperRepository.getOne(id);
    }

    @Override
    public boolean blockCamper(Long id) {
        Camper camper = camperRepository.getOne(id);
        List<Reservation> pendingReservations = reservationRepository.findPendingReservations();

        if (camper != null) {
            if (!pendingReservations.isEmpty()) {
                for (Reservation r : pendingReservations) {
                    r.setReservationType(ReservationType.REJECTED);
                    reservationRepository.save(r);
                }
            }
            camper.setUserStatus(UserStatus.BLOCKED);
            camper.setEnabled(false);
            camperRepository.save(camper);
            return true;
        }
        return false;
    }

    @Override
    public List<Camper> findCampersWhoCanceledReservations() {
        List<Camper> campers = findActiveCampers();
        List<Camper> campersWithCanceledReservations = new ArrayList<>();

        for (Camper c : campers) {
            int counter = 0;
            for (Reservation r : c.getReservations()) {
                if (r.getReservationType().equals(ReservationType.CANCELED)) {
                    counter++;
                }
            }
            if (counter >= 3) {
                campersWithCanceledReservations.add(c);
            }
        }
        return campersWithCanceledReservations;
    }

    @Override
    public List<Camper> findAll() {
        return camperRepository.findAll();
    }

    @Override
    public List<Camper> findActiveCampers() {
        List<Camper> campers = camperRepository.findAll();
        List<Camper> activeCampers = new ArrayList<>();

        for (Camper c : campers) {
            if (c.getUserStatus().equals(UserStatus.ACTIVE)) {
                activeCampers.add(c);
            }
        }
        return activeCampers;
    }

    @Override
    public int campersWhoCanceledReservations() {
        List<Camper> campers = findCampersWhoCanceledReservations();
        return campers.size();
    }

    //kamperi koji prate kampera sa idFollowed
    @Override
    public List<Camper> getFollowers(Long idFollowed) {
        List<Long> idFollowers = followingRepository.findFollowersById(idFollowed);
        List<Camper> followers = new ArrayList<>();

        for (Long l : idFollowers) {
            Camper camper = camperRepository.getOne(l);
            if (camper != null) {
                followers.add(camper);
            }
        }

        return followers;
    }

    //kamperi koji su praceni od strane kampera sa idFollower
    @Override
    public List<Camper> getFollowing(Long idFollower) {
        List<Long> idFollowing = followingRepository.findFollowingById(idFollower);
        List<Camper> following = new ArrayList<>();

        for (Long l : idFollowing) {
            Camper camper = camperRepository.getOne(l);
            if (camper != null) {
                following.add(camper);
            }
        }

        return following;
    }

    @Override
    public List<Camper> findByNameAndSurname(String name, String surname) {
        if(surname.equals("null")) {
            return camperRepository.findByName(name);
        }
        return camperRepository.findByNameAndSurname(name, surname);
    }

}
