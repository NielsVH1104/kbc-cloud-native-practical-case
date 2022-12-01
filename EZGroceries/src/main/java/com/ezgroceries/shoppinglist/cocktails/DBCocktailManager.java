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

    @Autowired
    private CocktailDBClient cocktailDBClient;

    @Autowired
    private CocktailService cocktailService;

    @Override
    public List<Cocktail> getAllCocktails(String searchValue) {
        CocktailDBResponse response = cocktailDBClient.searchCocktails(searchValue);
        List<Cocktail> cocktailList = cocktailService.mergeCocktails(response.getDrinks());
        return cocktailList;
    }

    @Override
    public Cocktail getCocktail(String id) {
        return cocktailService.findCocktailByID(id);
    }
}
