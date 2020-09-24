package com.app.campapp.repository;

import com.app.campapp.model.Caterer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CatererRepository extends JpaRepository<Caterer, Long> {
}
