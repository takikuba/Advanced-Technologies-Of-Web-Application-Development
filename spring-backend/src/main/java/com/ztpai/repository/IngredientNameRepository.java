package com.ztpai.repository;

import com.ztpai.model.IngredientName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientNameRepository extends JpaRepository<IngredientName, Long> {
    boolean existsIngredientNameByName(String newIngredient);
    IngredientName findIngredientNameByName(String name);
}
