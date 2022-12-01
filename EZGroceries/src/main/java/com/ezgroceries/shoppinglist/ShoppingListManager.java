package com.ezgroceries.shoppinglist;

import com.ezgroceries.cocktails.Cocktail;
import com.ezgroceries.meals.Meal;

import java.util.List;
import java.util.UUID;

public interface ShoppingListManager {

    List<ShoppingList> getAllShoppingLists();

    ShoppingList getShoppingList(String uuidString);

    UUID addNewList(String name);

    void addCocktailToShoppingList(ShoppingList list, Cocktail cocktail);
    void addMealToShoppingList(ShoppingList shoppingList, Meal meal);
}
