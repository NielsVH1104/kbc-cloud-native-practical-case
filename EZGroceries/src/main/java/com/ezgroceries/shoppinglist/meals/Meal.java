package com.ezgroceries.shoppinglist.meals;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Meal {

    private UUID uuid;
    private final String name;

    private String category;

    private String instructions;

    private String image;

    private List<String> ingredients = new ArrayList<>();

    public Meal(String name){
        this.name = name;
        ingredients = new ArrayList<>();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(String ingredient){
        ingredients.add(ingredient);
    }
}
