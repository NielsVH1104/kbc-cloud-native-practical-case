package com.ezgroceries.meals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MealController {

    private static final Logger log = LoggerFactory.getLogger(MealController.class);

    @Autowired
    private MealManager mealManager;

    @GetMapping(value = "/meals")
    public List<Meal> mealList(@RequestParam String search) {
        log.info("mealList called with search-parameter: {}", search);
        return mealManager.getAllMeals(search);
    }
}
