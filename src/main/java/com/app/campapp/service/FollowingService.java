package com.app.campapp.service;

import com.app.campapp.model.Following;

public interface FollowingService {

    Following follow(Long idCamperFollower, Long idCamperFollowed);
}
