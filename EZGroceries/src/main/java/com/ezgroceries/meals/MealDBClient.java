package com.ezgroceries.meals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Component
@FeignClient(name = "MealDBClient", url = "https://www.themealdb.com/api/json/v1/1/",fallback = MealDBClient.MealDbClientFallback.class)
public interface MealDBClient {

    @GetMapping(value = "search.php")
    MealDBResponse searchMeals(@RequestParam("s") String search);

    @Component
    class MealDbClientFallback implements MealDBClient{

        private static final Logger log = LoggerFactory.getLogger(MealDbClientFallback.class);

        @Autowired
        private MealRepository mealRepository;

        @Override
        public MealDBResponse searchMeals(String search) {
            log.info("external website failed. Using fallback Method");
            List<MealEntity> entities = mealRepository.findByNameContainingIgnoreCase(search);

            MealDBResponse mealDBResponse = new MealDBResponse();
            mealDBResponse.setMeals(entities.stream().map(mealEntity -> {
                MealDBResponse.MealResource mealResource = new MealDBResponse.MealResource();
                mealResource.setIdMeal(mealEntity.getIdMeal());
                mealResource.setStrMeal(mealEntity.getName());
                mealResource.setStrMealThumb(mealEntity.getImage());
                mealResource.setStrInstructions(mealEntity.getInstructions());
                mealResource.setStrCategory(mealEntity.getCategory());
                int i=0;
                for(String ingredient: mealEntity.getIngredients()){
                    i++;
                    switch (i){
                        case 1: mealResource.setStrIngredient1(ingredient); break;
                        case 2: mealResource.setStrIngredient2(ingredient); break;
                        case 3: mealResource.setStrIngredient3(ingredient); break;
                        case 4: mealResource.setStrIngredient4(ingredient); break;
                        case 5: mealResource.setStrIngredient5(ingredient); break;
                        case 6: mealResource.setStrIngredient6(ingredient); break;
                        case 7: mealResource.setStrIngredient7(ingredient); break;
                        case 8: mealResource.setStrIngredient8(ingredient); break;
                        case 9: mealResource.setStrIngredient9(ingredient); break;
                        case 10: mealResource.setStrIngredient10(ingredient); break;
                        case 11: mealResource.setStrIngredient11(ingredient); break;
                        case 12: mealResource.setStrIngredient12(ingredient); break;
                        case 13: mealResource.setStrIngredient13(ingredient); break;
                        case 14: mealResource.setStrIngredient14(ingredient); break;
                        case 15: mealResource.setStrIngredient15(ingredient); break;
                        default:break;
                    }
                }
                return mealResource;
            }).collect(Collectors.toList()));

            return mealDBResponse;
        }
    }
}
