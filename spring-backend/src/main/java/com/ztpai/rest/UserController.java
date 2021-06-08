package com.ztpai.rest;

import com.ztpai.exception.ResourceConflictException;
import com.ztpai.exception.ResourceNotFoundException;
import com.ztpai.model.*;
import com.ztpai.repository.*;
import com.ztpai.request.IngredientRequest;
import com.ztpai.request.RecipeRequest;
import com.ztpai.request.TagRequest;
import com.ztpai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

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
    private IngredientRepository ingredientRepo;
    @Autowired
    private RecipeTagsRepository recipeTagRepo;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

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

    @GetMapping("/ingredients")
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
    public List<Recipe> addRecipe(@RequestBody RecipeRequest recipe){
        Recipe newRecipe = new Recipe();
        newRecipe.setName(recipe.getName());
        System.out.println("recipe name: " + recipe.getName());
        newRecipe.setTime(recipe.getTime());
        System.out.println("recipe time:  "+ recipe.getTime());
        newRecipe.setKcal(recipe.getKcal());
        newRecipe.setDescription(recipe.getDescription());
        newRecipe.setImage(recipe.getImage());
        newRecipe.setLink(recipe.getLink());

        List<Ingredient> newIngredients = new ArrayList<>();
        for(IngredientRequest i: recipe.getIngredients()){
            System.out.println("Ingredient: " + i.getName() + " " + i.getCount() + " " + i.getUnit());
            newIngredients.add(new Ingredient(ingredientNameRepo.findIngredientNameByName(i.getName()), i.getCount(), unitRepo.findUnitByName(i.getUnit())));
        }
        newRecipe.setIngredients(newIngredients);

        recipeRepo.save(newRecipe);

        List<Tag> newTags = new ArrayList<>();
        for(TagRequest t: recipe.getTags()){
            Tag t2 = tagRepo.getByName(t.getName());
            newTags.add(t2);
//            recipeTagRepo.save(new RecipeTags(newRecipe.getId(), t2.getId()));
        }
        newRecipe.setTags(newTags);

        System.out.println(newRecipe);
        return recipeRepo.findAll();
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

        recipeRepo.save(recipe);
        return ResponseEntity.ok(updateRecipe);
    }

    @PutMapping("/recipes/{id}/like")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity.BodyBuilder addLike(@PathVariable long id){
        recipeRepo.getOne(id).addLike();
        return ResponseEntity.ok();
    }

    @PostMapping("/recipes/{id}/dislike")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity.BodyBuilder addDisLike(@PathVariable long id, @RequestBody long idBody){
        recipeRepo.getOne(id).addDislike();
        return ResponseEntity.ok();
    }

    public void sendMessage(String msg) {
        kafkaTemplate.send(kafkaTemplate.getDefaultTopic(), msg);
    }

    @KafkaListener(topics = "tutorilspoint", groupId = "group-id")
    public void listen(String message){
        System.out.println("New message from: " + message);
    }


}
