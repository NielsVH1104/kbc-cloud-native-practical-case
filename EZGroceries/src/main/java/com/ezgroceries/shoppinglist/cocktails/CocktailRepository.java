package com.ezgroceries.shoppinglist.cocktails;

import org.springframework.data.repository.Repository;

import java.util.UUID;

public interface CocktailRepository extends Repository<CocktailEntity, UUID> {

    public CocktailEntity findByCocktailId(UUID Id);
    public CocktailEntity findByIdDrink(String idDrink);
    public void save(CocktailEntity cocktail);
}
