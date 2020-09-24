package com.app.campapp.service;

public interface FollowingService {

    boolean follow(Long idFollower, Long idFollowed);
    boolean alreadyFollow(Long idFollower, Long idFollowed);
}
