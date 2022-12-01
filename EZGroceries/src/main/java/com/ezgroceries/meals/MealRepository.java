package com.ezgroceries.meals;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.UUID;

public interface MealRepository extends Repository<MealEntity, UUID> {

    MealEntity findByMealId(UUID uuid);

    MealEntity findByIdMeal(String id);

    void save(MealEntity meal);

    List<MealEntity> findByNameContainingIgnoreCase(String search);
}
