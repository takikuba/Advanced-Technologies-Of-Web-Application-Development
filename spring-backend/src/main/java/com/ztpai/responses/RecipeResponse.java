package com.ztpai.responses;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ztpai.model.Ingredient;
import com.ztpai.model.Recipe;
import com.ztpai.model.Tag;

import java.util.List;

public class RecipeResponse {

    @JsonIgnore
    private Recipe recipe;

    public RecipeResponse(Recipe recipe){
        this.recipe = recipe;
    }

    public long getId() {
        return recipe.getId();
    }

    public void setId(long id) {
        recipe.setId(id);
    }

    public String getName() {
        return recipe.getName();
    }

    public void setName(String name) {
        recipe.setName(name);
    }

    public String getDescription() {
        return recipe.getDescription();
    }

    public void setDescription(String description) {
        recipe.setDescription(description);
    }

    public int getLikeB() {
        return recipe.getLikeB();
    }

    public void setLikeB(int like) {
        recipe.setLikeB(like);
    }

    public int getDislikeB() {
        return recipe.getDislikeB();
    }

    public void setDislikeB(int dislike) {
        recipe.setDislikeB(dislike);
    }

    public int getKcal() {
        return recipe.getKcal();
    }

    public void setKcal(int kcal) {
        recipe.setKcal(kcal);
    }

    public int getTime() {
        return recipe.getTime();
    }

    public void setTime(int time) {
        recipe.setTime(time);
    }

    public String getImage() {
        return recipe.getImage();
    }

    public void setImage(String image) {
        recipe.setImage(image);
    }

    public String getLink() {
        return recipe.getLink();
    }

    public void setLink(String link) {
        recipe.setLink(link);
    }

    public List<Ingredient> getIngredients() {
        return recipe.getIngredients();
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.recipe.setIngredients(ingredients);
    }

    public List<Tag> getTags() {
        return recipe.getTags();
    }

    public void setTags(List<Tag> tags) {
        this.recipe.setTags(tags);
    }

}
