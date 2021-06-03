package com.ztpai.rest;

import com.ztpai.exception.ExceptionResponse;
import com.ztpai.exception.ResourceConflictException;
import com.ztpai.exception.ResourceNotFoundException;
import com.ztpai.model.*;
import com.ztpai.repository.*;
import com.ztpai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    @Autowired
    private IngredientNameRepository ingredientNameRepo;
    @Autowired
    private TagRepository tagRepo;
    @Autowired
    private UnitRepository unitRepo;
    @Autowired
    private RecipeRepository recipeRepo;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = GET, value = "/users/{userId}")
    public User loadById(@PathVariable Long userId) {
        return this.userService.findById(userId);
    }

    @RequestMapping(method = POST, value = "/signup")
    public ResponseEntity<?> addUser(@RequestBody UserRequest userRequest,
                                     UriComponentsBuilder ucBuilder) {

        User existUser = this.userService.findByUsername(userRequest.getUsername());
        if (existUser != null) {
            throw new ResourceConflictException(userRequest.getId(), "Username already exists");
        }
        User user = this.userService.save(userRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{userId}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @RequestMapping("/whoami")
    @PreAuthorize("hasRole('USER')")
    public User user() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @RequestMapping(method = GET, value = "/ingredients")
    @PreAuthorize("hasRole('USER')")
    public List<IngredientName> getIngredients(){
        return ingredientNameRepo.findAll();
    }

    @GetMapping(value = "/tags")
    @PreAuthorize("hasRole('USER')")
    public List<Tag> getTags(){
        return tagRepo.findAll();
    }

    @GetMapping(value = "/units")
    @PreAuthorize("hasRole('USER')")
    public List<Unit> getUnits(){
        return unitRepo.findAll();
    }

    @DeleteMapping(value = "/recipes/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity.BodyBuilder deleteMyRecipe(@PathVariable long id){
        if(user().getRecipes().contains(recipeRepo.getOne(id))){
            recipeRepo.deleteById(id);
            return ResponseEntity.ok();
        }
        return ResponseEntity.badRequest();
    }

    @PostMapping(value = "/recipes")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> addRecipe(@RequestBody Recipe recipe){
        return ResponseEntity.ok(recipeRepo.save(recipe));
    }

    @PutMapping("/recipes/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable long id, @RequestBody Recipe updateRecipe){
        Recipe recipe = recipeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe with id not exist!"));
        recipe.setIngredients(updateRecipe.getIngredients());
        recipe.setTags(updateRecipe.getTags());
        recipe.setDescription(updateRecipe.getDescription());
        recipe.setName(updateRecipe.getName());
        recipe.setLink(updateRecipe.getLink());
        recipe.setImage(updateRecipe.getImage());
        recipe.setKcal(updateRecipe.getKcal());
        recipe.setTime(updateRecipe.getTime());
        recipe.setLikeB(updateRecipe.getLikeB());
        recipe.setDislikeB(updateRecipe.getDislikeB());

        Recipe newRecipe = recipeRepo.save(recipe);
        return ResponseEntity.ok(updateRecipe);
    }

    @PutMapping("/recipes/{id}/like")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity.BodyBuilder addLike(@PathVariable long id){
        recipeRepo.getOne(id).addLike();
        return ResponseEntity.ok();
    }

    @PutMapping("/recipes/{id}/dislike")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity.BodyBuilder addDisLike(@PathVariable long id){
        recipeRepo.getOne(id).addDislike();
        return ResponseEntity.ok();
    }


}
