package com.ezgroceries.shoppinglist.list;

import com.ezgroceries.shoppinglist.cocktails.Cocktail;
import com.ezgroceries.shoppinglist.meals.Meal;

import java.util.List;
import java.util.UUID;

public interface ShoppingListManager {

    public List<ShoppingList> getAllShoppingLists();

    public ShoppingList getShoppingList(String uuidString);

    public UUID addNewList(String name);

    public void addCocktailToShoppingList(ShoppingList list, Cocktail cocktail);
    void addMealToShoppingList(ShoppingList shoppingList, Meal meal);
}
