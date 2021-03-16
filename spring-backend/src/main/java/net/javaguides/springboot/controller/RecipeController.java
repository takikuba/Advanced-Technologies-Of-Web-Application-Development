package net.javaguides.springboot.controller;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Recipe;
import net.javaguides.springboot.respository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping("/recipes")
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    // create recipe
    @PostMapping("/recipes")
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    //get recipe by id rest api
    @GetMapping("/recipes/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Recipe with id: " + id +" not exist!"));
        return ResponseEntity.ok(recipe);
    }

    //RA update user
    @PutMapping("/recipes/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @RequestBody Recipe recipeUpdate) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User with id: " + id +" not exist!"));
        recipe.setName(recipeUpdate.getName());
        recipe.setDescription(recipeUpdate.getDescription());
        recipe.setLikeB(recipeUpdate.getLikeB());
        recipe.setDislikeB(recipeUpdate.getDislikeB());
        recipe.setKcal(recipeUpdate.getKcal());
        recipe.setTime(recipeUpdate.getTime());
        recipe.setImage(recipeUpdate.getImage());
        recipe.setLink(recipeUpdate.getLink());
        recipe.setIdAssignedBy(recipeUpdate.getIdAssignedBy());

        return ResponseEntity.ok(recipeRepository.save(recipe));
    }

    //RA delete user
    @DeleteMapping("/recipes/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteRecipe(@PathVariable Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User with id: " + id +" not exist!"));

        recipeRepository.delete(recipe);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users/{id}/recipes")
    public ResponseEntity<List<Recipe>> getRecipeByUser(@PathVariable long id) {
        List<Recipe> recipes = recipeRepository.findAll();
        recipes.removeIf(r -> r.getIdAssignedBy() != id);
        return ResponseEntity.ok(recipes);
    }
}
