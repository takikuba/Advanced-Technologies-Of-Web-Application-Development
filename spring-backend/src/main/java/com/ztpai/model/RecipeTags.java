package com.ztpai.model;

import javax.persistence.*;

@Entity
@Table(name = "recipe_tags")
public class RecipeTags {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name="id_recipe")
    private long idRecipe;
    @Column(name="id_tags")
    private long idTags;

    public RecipeTags(){}

    public RecipeTags(long idRecipe, long idTags){
        this.idRecipe = idRecipe;
        this.idTags = idTags;
    }

    public int getIdRecipe() {
        return (int) idRecipe;
    }

    public void setIdRecipe(int idRecipe) {
        this.idRecipe = idRecipe;
    }

    public int getIdTags() {
        return (int) idTags;
    }

    public void setIdTags(int idTags) {
        this.idTags = idTags;
    }
}
