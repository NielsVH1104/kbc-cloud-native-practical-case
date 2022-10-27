package com.ezgroceries.shoppinglist.cocktails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CocktailController {

    private final CocktailManager cocktailManager;

    @Autowired
    public CocktailController(CocktailManager cocktailManager){
        this.cocktailManager=cocktailManager;
    }
}
