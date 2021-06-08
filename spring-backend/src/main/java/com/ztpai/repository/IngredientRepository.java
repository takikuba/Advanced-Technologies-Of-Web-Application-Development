package com.ztpai.repository;

import com.ztpai.model.Ingredient;
import com.ztpai.model.IngredientName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    Ingredient getIngredientByName(IngredientName name);

}

