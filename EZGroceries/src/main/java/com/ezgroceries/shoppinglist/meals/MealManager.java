package com.ezgroceries.shoppinglist.meals;

import java.util.List;

public interface MealManager {

    public List<Meal> getAllMeals(String search);
    public Meal getMeal(String uuid);
}
