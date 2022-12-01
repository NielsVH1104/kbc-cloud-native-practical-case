package com.ezgroceries.cocktails;

import java.util.List;

public interface CocktailManager {

    List<Cocktail> getAllCocktails(String searchValue);
    Cocktail getCocktail(String id);
}
