package com.app.campapp.repository;

import com.app.campapp.model.Following;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowingRepository extends JpaRepository<Following, Long> {
}
