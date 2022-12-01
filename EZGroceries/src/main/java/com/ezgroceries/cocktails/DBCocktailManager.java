package com.ezgroceries.cocktails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("db")
public class DBCocktailManager implements CocktailManager{

    @Autowired
    private CocktailDBClient cocktailDBClient;

    @Autowired
    private CocktailService cocktailService;

    @Override
    public List<Cocktail> getAllCocktails(String searchValue) {
        CocktailDBResponse response = cocktailDBClient.searchCocktails(searchValue);
        return cocktailService.mergeCocktails(response.getDrinks());
    }

    @Override
    public Cocktail getCocktail(String id) {
        return cocktailService.findCocktailByID(id);
    }
}
