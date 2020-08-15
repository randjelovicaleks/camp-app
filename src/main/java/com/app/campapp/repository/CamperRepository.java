package com.app.campapp.repository;

import com.app.campapp.model.Camper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CamperRepository extends JpaRepository<Camper, Long> {
}
