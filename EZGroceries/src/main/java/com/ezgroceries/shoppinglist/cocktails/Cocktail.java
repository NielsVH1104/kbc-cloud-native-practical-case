package com.ezgroceries.shoppinglist.cocktails;

import java.util.List;

public class Cocktail {

    private String cocktailId;
    private String name;
    private String glass;
    private String instructions;
    private String image;
    private List<String> ingredients;

    public String getCocktailId() {
        return cocktailId;
    }

    public void setCocktailId(String cocktailId) {
        this.cocktailId = cocktailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if(!ingredients.contains(newIngredient)){
            ingredients.add(newIngredient);
        }
    }

    public void removeIngredient(String tobeRemoved){
        for(String ingredient: ingredients){
            if(ingredient.equalsIgnoreCase(tobeRemoved)){
                ingredients.remove(ingredient);
            }
        }
    }
}
