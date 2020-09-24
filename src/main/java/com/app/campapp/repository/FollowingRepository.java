package com.app.campapp.repository;

import com.app.campapp.model.Following;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowingRepository extends JpaRepository<Following, Long> {

    @Query(value = "SELECT follower_id FROM following WHERE followed_id = ?1", nativeQuery = true)
    List<Long> findFollowersById(Long idFollowed);

    @Query(value = "SELECT followed_id FROM following WHERE follower_id = ?1", nativeQuery = true)
    List<Long> findFollowingById(Long idFollower);
}
