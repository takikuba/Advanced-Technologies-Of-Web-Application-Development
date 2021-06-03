package com.ztpai.rest;

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
import java.util.Map;

@RestController
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
    public Tag addTag(@RequestBody Tag tag){
        return tagRepo.save(tag);
    }

    @DeleteMapping("/tags")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity.BodyBuilder deleteTag(@RequestBody Tag tag){
        tagRepo.delete(tag);
        return ResponseEntity.ok();
    }

    @PostMapping("/units")
    @PreAuthorize("hasRole('ADMIN')")
    public Unit addUnit(@RequestBody Unit unit){
        return unitRepo.save(unit);
    }

    @DeleteMapping("/units")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity.BodyBuilder deleteUnit(@RequestBody Unit unit){
        unitRepo.delete(unit);
        return ResponseEntity.ok();
    }

    @PostMapping("/ingredients")
    @PreAuthorize("hasRole('ADMIN')")
    public IngredientName addIngredient(@RequestBody IngredientName ingredient){
        return ingredientNameRepo.save(ingredient);
    }

    @DeleteMapping("/ingredients")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity.BodyBuilder deleteIngredient(@RequestBody IngredientName ingredient){
        ingredientNameRepo.delete(ingredient);
        return ResponseEntity.ok();
    }



}
