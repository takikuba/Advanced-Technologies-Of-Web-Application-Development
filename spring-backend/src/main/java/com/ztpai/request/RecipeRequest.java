package com.ztpai.responses;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ztpai.model.Ingredient;
import com.ztpai.model.Recipe;
import com.ztpai.model.Tag;
import org.springframework.context.annotation.Bean;

import javax.persistence.Entity;
import java.util.List;

public class RecipeResponse {

    private String name;
    private String description;
    private String image;
    private int kcal;
    private int time;
    private String link;
    private List<TagResponse> tags;
    private List<IngredientResponse> ingredients;
    private int dislikeB;
    private int likeB;

    public RecipeResponse() {
    }

    public RecipeResponse(String name, String description, String image, int kcal, int time, String link, List<TagResponse> tags, List<IngredientResponse> ingredients, int dislikeB, int likeB) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.kcal = kcal;
        this.time = time;
        this.link = link;
        this.tags = tags;
        this.ingredients = ingredients;
        this.dislikeB = dislikeB;
        this.likeB = likeB;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<TagResponse> getTags() {
        return tags;
    }

    public void setTags(List<TagResponse> tags) {
        this.tags = tags;
    }

    public List<IngredientResponse> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientResponse> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "RecipeResponse{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", kcal=" + kcal +
                ", time=" + time +
                ", link='" + link + '\'' +
                ", tags=" + tags +
                ", ingredients=" + ingredients +
                ", dislikeB=" + dislikeB +
                ", likeB=" + likeB +
                '}';
    }
}
