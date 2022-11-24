package com.ezgroceries.shoppinglist.cocktails;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.UUID;

public interface CocktailRepository extends Repository<CocktailEntity, UUID> {

    public CocktailEntity findByCocktailId(UUID Id);
    public CocktailEntity findByIdDrink(String idDrink);
    public void save(CocktailEntity cocktail);
    public List<CocktailEntity> findByNameContainingIgnoreCase(String search);
}
