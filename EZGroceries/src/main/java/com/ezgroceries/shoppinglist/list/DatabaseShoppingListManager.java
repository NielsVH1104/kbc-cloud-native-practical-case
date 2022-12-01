package com.ezgroceries.shoppinglist.list;

import com.ezgroceries.shoppinglist.cocktails.Cocktail;
import com.ezgroceries.shoppinglist.meals.Meal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Repository
public class DatabaseShoppingListManager implements ShoppingListManager{

    private static final Logger log = LoggerFactory.getLogger(DatabaseShoppingListManager.class);

    @Autowired private ShoppingListService shoppingListService;


    @Override
    public List<ShoppingList> getAllShoppingLists() {
        try{
            return shoppingListService.getAllLists();
        }catch(NullPointerException ex){
            return new ArrayList<>();
        }
    }

    @Override
    public ShoppingList getShoppingList(String uuidString) {
        return shoppingListService.findExistingList(UUID.fromString(uuidString));
    }

    @Override
    public UUID addNewList(String name) {
        log.info("addNewList triggered with name " + name);
        ShoppingList newList = shoppingListService.createNew(name);
        return newList.getShoppingListId();
    }

    @Override
    public void addCocktailToShoppingList(ShoppingList list, Cocktail cocktail){
        shoppingListService.addCocktailToList(list, cocktail);
    }

    @Override
    public void addMealToShoppingList(ShoppingList shoppingList, Meal meal) {
        shoppingListService.addMealToList(shoppingList, meal);
    }
}
