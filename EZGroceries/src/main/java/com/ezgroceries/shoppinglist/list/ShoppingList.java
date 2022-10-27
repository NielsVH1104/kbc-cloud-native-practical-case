package com.ezgroceries.shoppinglist.list;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShoppingList {

    private UUID shoppingListId;
    private String name;
    private final List<String> ingredients = new ArrayList<>();

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

    public List<String> getIngredients() {
        return ingredients;
    }

    public void addIngredient(String newIngredient) {
        ingredients.add(newIngredient);
    }

}
