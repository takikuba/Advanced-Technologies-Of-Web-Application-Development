package net.javaguides.springboot.respository;

import net.javaguides.springboot.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long>{

    @Query(value = "SELECT * from public.ingredients inner join public.recipe_ingredients on recipe_ingredients.id_ingredients = ingredients.id where recipe_ingredients.id_recipe = ?1", nativeQuery = true)
    List<Ingredient> getIngredientByRecipe(Long id);

}
