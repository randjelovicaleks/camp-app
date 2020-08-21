package com.app.campapp.repository;

import com.app.campapp.model.Camper;
import com.app.campapp.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CamperRepository extends JpaRepository<Camper, Long> {

}
