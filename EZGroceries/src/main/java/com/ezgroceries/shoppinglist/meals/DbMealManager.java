package com.ezgroceries.shoppinglist.meals;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbMealManager implements MealManager{

    @Autowired
    private MealDBClient mealDBClient;

    @Autowired
    private MealService mealService;

    @Override
    public List<Meal> getAllMeals(String search) {
        MealDBResponse mealDBResponse = mealDBClient.searchMeals(search);
        List<Meal> meals = mealService.mergeMeals(mealDBResponse);
        return meals;
    }

    @Override
    public Meal getMeal(String uuid) {
        return mealService.findMealById(uuid);
    }
}
