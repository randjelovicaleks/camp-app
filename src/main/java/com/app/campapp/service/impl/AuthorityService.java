package com.app.campapp.service.impl;

import com.app.campapp.model.Authority;
import com.app.campapp.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    public List<Authority> findById(Long id) {
        Authority auth = this.authorityRepository.getOne(id);
        List<Authority> auths = new ArrayList<>();
        auths.add(auth);
        return auths;
    }

    public List<Authority> findByRole(String role) {
        Authority auth = this.authorityRepository.findByRole(role);
        List<Authority> auths = new ArrayList<>();
        auths.add(auth);
        return auths;
    }
}
