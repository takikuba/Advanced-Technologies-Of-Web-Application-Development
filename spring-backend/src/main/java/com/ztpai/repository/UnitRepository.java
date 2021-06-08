package com.ztpai.repository;

import com.ztpai.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {
    Unit findUnitByName(String name);
}

