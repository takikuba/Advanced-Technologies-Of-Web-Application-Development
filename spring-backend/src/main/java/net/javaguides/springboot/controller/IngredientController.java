package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Ingredient;
import net.javaguides.springboot.respository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
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
}
