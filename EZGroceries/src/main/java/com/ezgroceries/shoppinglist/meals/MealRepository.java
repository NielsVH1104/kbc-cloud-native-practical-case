package com.ezgroceries.shoppinglist.meals;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.UUID;

public interface MealRepository extends Repository<MealEntity, UUID> {

    public MealEntity findByMealId(UUID uuid);

    public MealEntity findByIdMeal(String id);

    public void save(MealEntity meal);

    public List<MealEntity> findByNameContainingIgnoreCase(String search);
}
