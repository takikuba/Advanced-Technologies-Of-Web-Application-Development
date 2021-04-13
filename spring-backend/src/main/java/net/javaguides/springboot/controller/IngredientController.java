package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Ingredient;
import net.javaguides.springboot.model.Recipe;
import net.javaguides.springboot.respository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin()
@RestController
@RequestMapping("/api/v1/")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    //Get all ingredients
    @GetMapping("/ingredients")
    public List<Ingredient> getAllIngredients(){
        return ingredientRepository.findAll();
    }

    //REST API create ingredient
    @PostMapping("/ingredients")
    public Ingredient createIngredient(@RequestBody Ingredient ingredient){
        return ingredientRepository.save(ingredient);
    }

    //get ingredients by recipe id
    @GetMapping("/recipes/{id}/ingredients")
    public List<Ingredient> getRecipeIngredients(@PathVariable long id) {
        return ingredientRepository.getIngredientByRecipe(id);
    }
}
