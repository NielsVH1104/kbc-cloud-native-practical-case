package com.ezgroceries.shoppinglist.cocktails;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StubCocktailManager implements CocktailManager{

    private final ArrayList<Cocktail> cocktails;

    public StubCocktailManager(){
        cocktails = new ArrayList<>();

        Cocktail margerita = new Cocktail("Margerita");
        margerita.setCocktailID("23b3d85a-3928-41c0-a533-6538a71e17c4");
        margerita.setGlass("Cocktail glass");
        margerita.setInstructions("Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..");
        margerita.setImage("https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg");
        margerita.addIngredient("Tequila");
        margerita.addIngredient("Triple sec");
        margerita.addIngredient("Lime juice");
        margerita.addIngredient("Salt");
        cocktails.add(margerita);

        Cocktail blue = new Cocktail("Blue Margerita");
        blue.setCocktailID("d615ec78-fe93-467b-8d26-5d26d8eab073");
        blue.setGlass("Cocktail glass");
        blue.setInstructions("Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..");
        blue.setImage("https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg");
        blue.addIngredient("Tequila");
        blue.addIngredient("Blue Curacao");
        blue.addIngredient("Lime juice");
        blue.addIngredient("Salt");
        cocktails.add(blue);
    }

    @Override
    public List<Cocktail> getAllCocktails() {
        return cocktails;
    }

    @Override
    public Cocktail getCocktail(String id) {
        for(Cocktail cocktail: cocktails){
            if(cocktail.getCocktailId().equals(id)){
                return cocktail;
            }
        }
        return null;
    }
}
