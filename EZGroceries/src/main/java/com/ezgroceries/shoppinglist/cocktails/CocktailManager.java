package com.ezgroceries.shoppinglist.cocktails;

import java.util.List;

public interface CocktailManager {

    public List<Cocktail> getAllCocktails(String searchValue);
    public Cocktail getCocktail(String id);
}
