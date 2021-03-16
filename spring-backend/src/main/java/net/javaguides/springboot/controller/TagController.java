package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Tag;
import net.javaguides.springboot.respository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
