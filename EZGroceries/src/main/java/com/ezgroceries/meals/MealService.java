package com.ezgroceries.meals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MealService {

    private static final Logger log = LoggerFactory.getLogger(MealService.class);

    @Autowired
    private MealRepository mealRepository;

    public List<Meal> mergeMeals(MealDBResponse mealDBResponse) {
        log.info("mergeMeals triggered");
        ArrayList<Meal> mergedMeals = new ArrayList<>();
        for(MealDBResponse.MealResource resource: mealDBResponse.getMeals()){
            log.info("--> {}", resource.getStrMeal());
            MealEntity entity = mealRepository.findByIdMeal(resource.getIdMeal());
            if(entity==null){
                log.info("-- -- entity does not exist yet. make a new one and persist it.");
                UUID newUUId = UUID.randomUUID();
                MealEntity newEntity = new MealEntity();
                log.info("-- -- {}", newUUId);
                newEntity.setMealId(newUUId);
                newEntity.setIdMeal(resource.getIdMeal());
                newEntity.setName(resource.getStrMeal());
                newEntity.setInstructions(resource.getStrInstructions());
                newEntity.setImage(resource.getStrMealThumb());
                newEntity.setCategory(resource.getStrCategory());
                if(resource.getStrIngredient1() != null){
                    newEntity.addIngredient(resource.getStrIngredient1());
                }
                if(resource.getStrIngredient1()!= null){
                    newEntity.addIngredient(resource.getStrIngredient1());
                }
                if(resource.getStrIngredient2()!= null){
                    newEntity.addIngredient(resource.getStrIngredient2());
                }
                if(resource.getStrIngredient3() != null){
                    newEntity.addIngredient(resource.getStrIngredient3());
                }
                if(resource.getStrIngredient4() != null){
                    newEntity.addIngredient(resource.getStrIngredient4());
                }
                if(resource.getStrIngredient5() != null){
                    newEntity.addIngredient(resource.getStrIngredient5());
                }
                if(resource.getStrIngredient6() != null){
                    newEntity.addIngredient(resource.getStrIngredient6());
                }
                if(resource.getStrIngredient7() != null){
                    newEntity.addIngredient(resource.getStrIngredient7());
                }
                if(resource.getStrIngredient8() != null){
                    newEntity.addIngredient(resource.getStrIngredient8());
                }
                if(resource.getStrIngredient9() != null){
                    newEntity.addIngredient(resource.getStrIngredient9());
                }
                if(resource.getStrIngredient10() != null){
                    newEntity.addIngredient(resource.getStrIngredient10());
                }
                if(resource.getStrIngredient11() != null){
                    newEntity.addIngredient(resource.getStrIngredient11());
                }
                if(resource.getStrIngredient12() != null){
                    newEntity.addIngredient(resource.getStrIngredient12());
                }
                if(resource.getStrIngredient13() != null){
                    newEntity.addIngredient(resource.getStrIngredient13());
                }
                if(resource.getStrIngredient14() != null){
                    newEntity.addIngredient(resource.getStrIngredient14());
                }
                if(resource.getStrIngredient15() != null){
                    newEntity.addIngredient(resource.getStrIngredient15());
                }

                mealRepository.save(newEntity);
                Meal meal = entityToMeal(newEntity);
                mergedMeals.add(meal);
            }else{
                log.info("-- -- entity found in database. Use existing UUID: {}", entity.getMealId());
                Meal meal = entityToMeal(entity);
                mergedMeals.add(meal);
            }

        }


        return mergedMeals;
    }

    public Meal findMealById(String ID) {
        log.info("getting meal with ID {}", ID);
        MealEntity entity = mealRepository.findByMealId(UUID.fromString(ID));
        return entityToMeal(entity);
    }

    private Meal entityToMeal(MealEntity entity){
        Meal meal = new Meal(entity.getName());
        meal.setImage(entity.getImage());
        meal.setCategory(entity.getCategory());
        meal.setInstructions(entity.getInstructions());
        meal.setUuid(entity.getMealId());
        meal.setIngredients(new ArrayList<>(entity.getIngredients()));
        return meal;
    }
}
