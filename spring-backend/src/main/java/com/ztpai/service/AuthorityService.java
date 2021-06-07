package com.ztpai.service;

import com.ztpai.model.Authority;
import com.ztpai.model.UserRoleName;

import java.util.List;

public interface AuthorityService {
  List<Authority> findById(Long id);

  List<Authority> findByName(UserRoleName name);

}
