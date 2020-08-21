package com.app.campapp.service.impl;

import com.app.campapp.model.Following;
import com.app.campapp.repository.FollowingRepository;
import com.app.campapp.service.FollowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowingServiceImpl implements FollowingService {

    @Autowired
    private FollowingRepository followingRepository;

    @Override
    public Following follow(Long idCamperFollower, Long idCamperFollowed) {
        Following following = new Following(idCamperFollower, idCamperFollowed);
        followingRepository.save(following);
        return following;
    }

}
