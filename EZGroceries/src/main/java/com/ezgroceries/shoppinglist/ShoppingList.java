package com.ezgroceries.shoppinglist;

import java.util.*;

public class ShoppingList {

    private UUID shoppingListId;
    private String name;
    private final Set<String> ingredients = new HashSet<>();

    public ShoppingList(String name){
        shoppingListId = UUID.randomUUID();
        this.name =name;
    }

    public UUID getShoppingListId(){
        return shoppingListId;
    }

    public String getName(){
        return name;
    }

    public void setShoppingListId(UUID shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void addIngredient(String newIngredient) {
        ingredients.add(newIngredient);
    }

}
