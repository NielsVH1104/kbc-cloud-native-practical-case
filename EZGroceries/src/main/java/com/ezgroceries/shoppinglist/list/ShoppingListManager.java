package com.ezgroceries.shoppinglist.list;

import java.util.List;
import java.util.UUID;

public interface ShoppingListManager {

    public List<ShoppingList> getAllShoppingLists();

    public ShoppingList getShoppingList(String uuidString);

    public UUID addNewList(String name);
}
