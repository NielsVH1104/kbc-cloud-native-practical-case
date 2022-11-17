package com.ezgroceries.shoppinglist.cocktails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CocktailController {

    private static final Logger log = LoggerFactory.getLogger(CocktailController.class);

    private final CocktailManager cocktailManager;

    @Autowired
    public CocktailController(CocktailManager cocktailManager){
        this.cocktailManager=cocktailManager;
    }

    @GetMapping(value = "/cocktails")
    public List<Cocktail> cocktailList(@RequestParam String search) {
        log.info("cocktailList called with search-parameter: " + search);
        return cocktailManager.getAllCocktails(search);
    }
}
