package com.ztpai.rest;

import com.ztpai.config.WebSecurityConfig;
import com.ztpai.exception.ResourceNotFoundException;
import com.ztpai.model.IngredientName;
import com.ztpai.model.Tag;
import com.ztpai.model.Unit;
import com.ztpai.model.User;
import com.ztpai.repository.IngredientNameRepository;
import com.ztpai.repository.TagRepository;
import com.ztpai.repository.UnitRepository;
import com.ztpai.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    @Autowired
    UserRepository userRepo;

    @Autowired
    IngredientNameRepository ingredientNameRepo;

    @Autowired
    TagRepository tagRepo;

    @Autowired
    UnitRepository unitRepo;

    @GetMapping(value = "/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getUsers(){
        return userRepo.findAll();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userUpdate) {
        User user = userRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User with id: " + id +" not exist!"));
        user.setFirstname(userUpdate.getFirstname());
        user.setLastname(userUpdate.getLastname());
        user.setDescription(userUpdate.getDescription());
        user.setPhone(userUpdate.getPhone());

        User updatedUser = userRepo.save(user);

        return ResponseEntity.ok(updatedUser);
    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " not exist!"));

        userRepo.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/tags")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Tag> addTag(@RequestBody Tag tag){
        System.out.println("add tags!");
        tagRepo.save(tag);
        return tagRepo.findAll();
    }

    @DeleteMapping("/tags")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Tag> deleteTag(@RequestBody Tag tag){
        tagRepo.delete(tag);
        return tagRepo.findAll();
    }

    @PostMapping("/units")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Unit> addUnit(@RequestBody Unit unit){
        unitRepo.save(unit);
        return unitRepo.findAll();
    }

    @DeleteMapping("/units")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Unit> deleteUnit(@RequestBody Unit unit){
        unitRepo.delete(unit);
        return unitRepo.findAll();
    }

    @PostMapping("/ingredients")
    @PreAuthorize("hasRole('ADMIN')")
    public List<IngredientName> addIngredient(@RequestBody IngredientName ingredient){
        ingredientNameRepo.save(ingredient);
        return ingredientNameRepo.findAll();
    }

    @DeleteMapping("/ingredients")
    @PreAuthorize("hasRole('ADMIN')")
    public List<IngredientName> deleteIngredient(@RequestBody IngredientName ingredient){
        ingredientNameRepo.delete(ingredient);
        return ingredientNameRepo.findAll();
    }

}
