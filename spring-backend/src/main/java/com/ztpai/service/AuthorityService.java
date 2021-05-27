package com.ztpai.service;

import com.ztpai.model.Authority;
import com.ztpai.model.UserRoleName;

import java.util.List;

/**
 * Created by fan.jin on 2016-11-07.
 */
public interface AuthorityService {
  List<Authority> findById(Long id);

  List<Authority> findByName(UserRoleName name);

}
