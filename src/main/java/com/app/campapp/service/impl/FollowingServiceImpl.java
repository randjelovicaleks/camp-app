package com.app.campapp.service.impl;

import com.app.campapp.model.Following;
import com.app.campapp.repository.FollowingRepository;
import com.app.campapp.service.FollowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowingServiceImpl implements FollowingService {

    @Autowired
    private FollowingRepository followingRepository;

    @Override
    public boolean follow(Long idFollower, Long idFollowed) {
        if (idFollower != null && idFollowed != null) {
            Following following = new Following(idFollower, idFollowed);
            followingRepository.save(following);
            return true;
        }
        return false;
    }

    @Override
    public boolean alreadyFollow(Long idFollower, Long idFollowed) {
        List<Following> followingList = followingRepository.findAll();
        for (Following f : followingList) {
            if (f.getFollowerId().equals(idFollower) && f.getFollowedId().equals(idFollowed)) {
                return true;
            }
        }
        return false;
    }

}
