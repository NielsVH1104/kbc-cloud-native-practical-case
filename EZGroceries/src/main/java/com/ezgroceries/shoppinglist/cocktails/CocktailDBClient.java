package com.ezgroceries.shoppinglist.cocktails;

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
@FeignClient(name = "cocktailDBClient", url = "https://www.thecocktaildb.com/api/json/v1/1/",fallback = CocktailDBClient.CocktailDBClientFallback.class)
public interface CocktailDBClient {

    @GetMapping(value = "search.php")
    CocktailDBResponse searchCocktails(@RequestParam("s") String search);


    @Component
    class CocktailDBClientFallback implements CocktailDBClient {

        private static final Logger log = LoggerFactory.getLogger(CocktailDBClientFallback.class);

        @Autowired
        private CocktailRepository cocktailRepository;

        @Override
        public CocktailDBResponse searchCocktails(String search) {
            log.info("www.thecocktaildb.com failed. Using fallback method!");
            List<CocktailEntity> cocktailEntities = cocktailRepository.findByNameContainingIgnoreCase(search);

            CocktailDBResponse cocktailDBResponse = new CocktailDBResponse();
            cocktailDBResponse.setDrinks(cocktailEntities.stream().map(cocktailEntity -> {
                CocktailDBResponse.DrinkResource drinkResource = new CocktailDBResponse.DrinkResource();
                drinkResource.setIdDrink(cocktailEntity.getIdDrink());
                drinkResource.setStrDrink(cocktailEntity.getName());
                drinkResource.setStrDrinkThumb(cocktailEntity.getImage());
                drinkResource.setStrInstructions(cocktailEntity.getInstructions());
                drinkResource.setStrGlass(cocktailEntity.getGlass());
                int i=0;
                for(String ingredient: cocktailEntity.getIngredients()){
                    i++;
                    switch (i){
                        case 1: drinkResource.setStrIngredient1(ingredient); break;
                        case 2: drinkResource.setStrIngredient2(ingredient); break;
                        case 3: drinkResource.setStrIngredient3(ingredient); break;
                        case 4: drinkResource.setStrIngredient4(ingredient); break;
                        case 5: drinkResource.setStrIngredient5(ingredient); break;
                        case 6: drinkResource.setStrIngredient6(ingredient); break;
                        case 7: drinkResource.setStrIngredient7(ingredient); break;
                        case 8: drinkResource.setStrIngredient8(ingredient); break;
                        case 9: drinkResource.setStrIngredient9(ingredient); break;
                        case 10: drinkResource.setStrIngredient10(ingredient); break;
                        case 11: drinkResource.setStrIngredient11(ingredient); break;
                        case 12: drinkResource.setStrIngredient12(ingredient); break;
                        case 13: drinkResource.setStrIngredient13(ingredient); break;
                        case 14: drinkResource.setStrIngredient14(ingredient); break;
                        case 15: drinkResource.setStrIngredient15(ingredient); break;
                        default:break;
                    }
                }
                return drinkResource;
            }).collect(Collectors.toList()));

            return cocktailDBResponse;
        }
    }

}
