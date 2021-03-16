package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Unit;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.respository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")

public class UnitController {

    @Autowired
    private UnitRepository unitRepository;

    // get all of units
    @GetMapping("/units")
    public List<Unit> getAllUnits(){
        return unitRepository.findAll();
    }

    // RestApi to create unit
    @PostMapping("/units")
    public Unit createUnit(@RequestBody Unit unit){
        return unitRepository.save(unit);
    }

}
