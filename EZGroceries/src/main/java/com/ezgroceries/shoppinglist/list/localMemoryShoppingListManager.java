package com.ezgroceries.shoppinglist.list;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Repository
public class localMemoryShoppingListManager implements ShoppingListManager{

    private final HashMap<String, ShoppingList> shoppingLists = new HashMap<>();


    @Override
    public List<ShoppingList> getAllShoppingLists() {
        return new ArrayList<>(shoppingLists.values());
    }

    @Override
    public ShoppingList getShoppingList(String uuidString) {
        return shoppingLists.get(uuidString);
    }

    @Override
    public UUID addNewList(String name) {
        ShoppingList newList = new ShoppingList(name);
        UUID uuid = newList.getShoppingListId();
        shoppingLists.put(uuid.toString(), newList);
        return uuid;
    }
}
