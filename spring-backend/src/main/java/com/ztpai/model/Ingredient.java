package com.ztpai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "count")
    private int count;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_name")
    IngredientName name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_unit")
    Unit unit;

    public Ingredient() {
    }

    public Ingredient(IngredientName name, int count, Unit unit) {
        this.name = name;
        this.count = count;
        this.unit = unit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name.getName();
    }

    public void setName(IngredientName name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
