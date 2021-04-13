package net.javaguides.springboot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import net.javaguides.springboot.model.Recipe;
import net.javaguides.springboot.model.Tag;
import net.javaguides.springboot.respository.TagRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@CrossOrigin( origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")

public class TagController {

    @Autowired
    private TagRepository tagRepository;

    //get all tags
    @GetMapping("/tags")
    public List<Tag> getAllTags(){
        return tagRepository.findAll();
    }

    //REST API create tag
    @PostMapping("/tags")
    public Tag createTag(@RequestBody Tag tag){
        return tagRepository.save(tag);
    }

    //get tags by recipe id
    @GetMapping("/recipes/{id}/tags")
    public List<Tag> getRecipeTags(@PathVariable long id) {
        return tagRepository.getTagsByRecipe(id);
    }

    // add tags to recipe
    @PostMapping("/recipes/{id}/tags")
    public Boolean createRecipeTag(@PathVariable long id, @RequestBody ArrayList<Tag> tags) {
        for(Tag t: tags) {
            tagRepository.saveRecipeTags(id, t.getId());
        }
        return true;
    }

}
