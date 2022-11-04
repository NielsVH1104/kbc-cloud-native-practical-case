package com.ezgroceries.shoppinglist.cocktails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@Profile("db")
public class DBCocktailManager implements CocktailManager{

    @Autowired private CocktailDBClient cocktailDBClient;

    private final HashMap<String, Cocktail> dbCocktailMap = new HashMap();
    private final HashMap<String, Cocktail> localCocktailMap = new HashMap();

    @Override
    public List<Cocktail> getAllCocktails(String searchValue) {
        CocktailDBResponse response = cocktailDBClient.searchCocktails(searchValue);
        List<Cocktail> cocktailList = new ArrayList<>();
        for(CocktailDBResponse.DrinkResource drink: response.getDrinks()){
            if(dbCocktailMap.get(drink.getIdDrink()) != null){
                cocktailList.add(dbCocktailMap.get(drink.getIdDrink()));
            }else{
                Cocktail cocktail = new Cocktail(drink.getStrDrink());
                cocktail.setGlass(drink.getStrGlass());
                cocktail.setImage(drink.getStrDrinkThumb());
                cocktail.setInstructions(drink.getStrInstructions());
                if(drink.getStrIngredient1() != null){
                    cocktail.addIngredient(drink.getStrIngredient1());
                }
                if(drink.getStrIngredient1()!= null){
                    cocktail.addIngredient(drink.getStrIngredient1());
                }
                if(drink.getStrIngredient2()!= null){
                    cocktail.addIngredient(drink.getStrIngredient2());
                }
                if(drink.getStrIngredient3() != null){
                    cocktail.addIngredient(drink.getStrIngredient3());
                }
                if(drink.getStrIngredient4() != null){
                    cocktail.addIngredient(drink.getStrIngredient4());
                }
                if(drink.getStrIngredient5() != null){
                    cocktail.addIngredient(drink.getStrIngredient5());
                }
                if(drink.getStrIngredient6() != null){
                    cocktail.addIngredient(drink.getStrIngredient6());
                }
                if(drink.getStrIngredient7() != null){
                    cocktail.addIngredient(drink.getStrIngredient7());
                }
                if(drink.getStrIngredient8() != null){
                    cocktail.addIngredient(drink.getStrIngredient8());
                }
                if(drink.getStrIngredient9() != null){
                    cocktail.addIngredient(drink.getStrIngredient9());
                }
                if(drink.getStrIngredient10() != null){
                    cocktail.addIngredient(drink.getStrIngredient10());
                }
                if(drink.getStrIngredient11() != null){
                    cocktail.addIngredient(drink.getStrIngredient11());
                }
                if(drink.getStrIngredient12() != null){
                    cocktail.addIngredient(drink.getStrIngredient12());
                }
                if(drink.getStrIngredient13() != null){
                    cocktail.addIngredient(drink.getStrIngredient13());
                }
                if(drink.getStrIngredient14() != null){
                    cocktail.addIngredient(drink.getStrIngredient14());
                }
                if(drink.getStrIngredient15() != null){
                    cocktail.addIngredient(drink.getStrIngredient15());
                }
                cocktailList.add(cocktail);
                dbCocktailMap.put(drink.getIdDrink(),cocktail);
                localCocktailMap.put(cocktail.getCocktailId().toString(),cocktail);
            }
        }
        return cocktailList;
    }

    @Override
    public Cocktail getCocktail(String id) {
        return localCocktailMap.get(id);
    }
}
