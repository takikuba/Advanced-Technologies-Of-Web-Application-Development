package com.ztpai.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "like_b")
    private int likeB;
    @Column(name = "dislike_b")
    private int dislikeB;
    @Column(name = "kcal")
    private int kcal;
    @Column(name = "time")
    private int time;
    @Column(name = "image")
    private String image;
    @Column(name = "link")
    private String link;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "recipe_ingredients",
            joinColumns = @JoinColumn(name = "id_recipe", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_ingredient", referencedColumnName = "id"))
    List<Ingredient> ingredients;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "recipe_tags",
            joinColumns = @JoinColumn(name = "id_recipe", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_tags", referencedColumnName = "id"))
    List<Tag> tags;

    public Recipe() {
    }

    public Recipe(String name, String description, int likeB, int dislikeB, int kcal, int time, String image, String link) {
        this.name = name;
        this.description = description;
        this.likeB = likeB;
        this.dislikeB = dislikeB;
        this.kcal = kcal;
        this.time = time;
        this.image = image;
        this.link = link;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLikeB() {
        return likeB;
    }

    public void setLikeB(int like) {
        this.likeB = like;
    }

    public int getDislikeB() {
        return dislikeB;
    }

    public void setDislikeB(int dislike) {
        this.dislikeB = dislike;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link.replace("watch?v=", "embed/");
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
