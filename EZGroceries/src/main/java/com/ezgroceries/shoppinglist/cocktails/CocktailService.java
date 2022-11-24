package com.ezgroceries.shoppinglist.cocktails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CocktailService {

    private static final Logger log = LoggerFactory.getLogger(CocktailService.class);

    private final CocktailRepository cocktailRepository;

    public CocktailService(CocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
    }

    public List<Cocktail> mergeCocktails(List<CocktailDBResponse.DrinkResource> drinkResources){
        log.info("mergeCocktails triggered,");
        ArrayList<Cocktail> mergedList = new ArrayList<>();

        for(CocktailDBResponse.DrinkResource resource: drinkResources){
            log.info("--> " + resource.getStrDrink());
            CocktailEntity entity = cocktailRepository.findByIdDrink(resource.getIdDrink());
            if(entity == null){
                log.info("-- -- entity does not exist yet. make a new one and persist it.");
                UUID newUUId = UUID.randomUUID();
                CocktailEntity newEntity = new CocktailEntity();
                log.info("-- -- " + newUUId);
                newEntity.setCocktailId(newUUId);
                newEntity.setName(resource.getStrDrink());
                newEntity.setIdDrink(resource.getIdDrink());
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

                newEntity.setGlass(resource.getStrGlass());
                newEntity.setInstructions(resource.getStrInstructions());
                newEntity.setImage(resource.getStrDrinkThumb());


                cocktailRepository.save(newEntity);
                //return Cocktail.

                Cocktail cocktail = entityToCocktail(newEntity);
                mergedList.add(cocktail);
            }else{
                log.info("-- -- entity found in database. Use existing UUID: " + entity.getCocktailId());
                Cocktail cocktail = entityToCocktail(entity);
                mergedList.add(cocktail);
            }
        }
        return mergedList;
    }

    public Cocktail findCocktailbyID(String ID){
        log.info("getting cocktail with ID: " + ID);
        CocktailEntity entity = cocktailRepository.findByCocktailId(UUID.fromString(ID));
        return entityToCocktail(entity);
    }

    private Cocktail entityToCocktail(CocktailEntity entity){
        Cocktail cocktail = new Cocktail(entity.getName());
        cocktail.setCocktailID(entity.getCocktailId().toString());
        cocktail.setIngredients(new ArrayList<>(entity.getIngredients()));
        cocktail.setInstructions(entity.getInstructions());
        cocktail.setGlass(entity.getGlass());
        cocktail.setImage(entity.getImage());
        return cocktail;
    }

}
