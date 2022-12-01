package com.ezgroceries.meals;

import java.util.List;

public interface MealManager {

    List<Meal> getAllMeals(String search);
    Meal getMeal(String uuid);
}
