package com.ezgroceries.shoppinglist.configuration;

import com.ezgroceries.shoppinglist.cocktails.CocktailManager;
import com.ezgroceries.shoppinglist.cocktails.StubCocktailManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public CocktailManager cocktailManager(){return new StubCocktailManager();}
}
