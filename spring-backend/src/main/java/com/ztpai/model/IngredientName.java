package com.ztpai.model;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Table(name = "ingredient_name")
public class IngredientName {

    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;
    @Column(name = "name")
    private String name;

    public IngredientName() {
    }

    public IngredientName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
