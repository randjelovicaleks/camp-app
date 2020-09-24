package com.app.campapp.repository;

import com.app.campapp.model.Camper;
import com.app.campapp.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CamperRepository extends JpaRepository<Camper, Long> {

    @Query(value = "SELECT * from user WHERE type='CAMPER' AND lower(name) LIKE %:name% AND lower(surname) LIKE %:surname%", nativeQuery = true)
    List<Camper> findByNameAndSurname(@Param("name") String name, @Param("surname") String surname);

    @Query(value = "SELECT * from user WHERE type='CAMPER' AND lower(name) LIKE %:name%", nativeQuery = true)
    List<Camper> findByName(@Param("name") String name);

}
