package com.ztpai.rest;

import com.ztpai.exception.ResourceNotFoundException;
import com.ztpai.model.Recipe;
import com.ztpai.model.Tag;
import com.ztpai.model.User;
import com.ztpai.repository.RecipeRepository;
import com.ztpai.repository.TagRepository;
import com.ztpai.repository.UserRepository;
import com.ztpai.responses.RecipeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class PublicController {

    @Autowired
    UserRepository userRepo;
    @Autowired
    RecipeRepository recipeRepo;
    @Autowired
    TagRepository tagRepo;

    @GetMapping(value = "/recipes")
    public List<Recipe> getRecipes(){
        return recipeRepo.findAll();
    }

    @GetMapping(value = "/recipes/{id}")
    public ResponseEntity<RecipeResponse> getRecipeById(@PathVariable Long id) {
        Recipe recipe = recipeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe with id: " + id + " not exist!"));
        return ResponseEntity.ok(new RecipeResponse(recipe));
    }
}
