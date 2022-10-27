package com.ezgroceries.shoppinglist.cocktails;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Cocktail {

    private UUID cocktailId;
    private final String name;
    private String glass;
    private String instructions;
    private String image;
    private List<String> ingredients = new ArrayList<>();

    public Cocktail(String name){
        this.name=name;
        this.cocktailId= UUID.randomUUID();
    }

    public UUID getCocktailId() {
        return cocktailId;
    }

    public void setCocktailID(String cocktailId){
        this.cocktailId=UUID.fromString(cocktailId);
    }

    public String getName() {
        return name;
    }

    public String getGlass() {
        return glass;
    }

    public void setGlass(String glass) {
        this.glass = glass;
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

    public void addIngredient(String newIngredient) {
            ingredients.add(newIngredient);
    }

}
