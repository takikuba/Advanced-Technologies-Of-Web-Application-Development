package com.ztpai.repository;

import com.ztpai.model.Authority;
import com.ztpai.model.UserRoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByName(UserRoleName name);
}
