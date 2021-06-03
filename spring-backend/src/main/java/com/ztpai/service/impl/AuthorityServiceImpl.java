package com.ztpai.service.impl;

import com.ztpai.model.Authority;
import com.ztpai.model.UserRoleName;
import com.ztpai.repository.AuthorityRepository;
import com.ztpai.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public List<Authority> findById(Long id) {

        Authority auth = this.authorityRepository.getOne(id);
        List<Authority> auths = new ArrayList<>();
        auths.add(auth);
        return auths;
    }

    @Override
    public List<Authority> findByName(UserRoleName name) {
        Authority auth = this.authorityRepository.findByName(name);
        List<Authority> auths = new ArrayList<>();
        auths.add(auth);
        return auths;
    }

}
