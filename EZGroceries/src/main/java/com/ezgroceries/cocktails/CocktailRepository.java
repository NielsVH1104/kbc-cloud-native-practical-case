package com.ezgroceries.cocktails;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.UUID;

public interface CocktailRepository extends Repository<CocktailEntity, UUID> {

    CocktailEntity findByCocktailId(UUID Id);
    CocktailEntity findByIdDrink(String idDrink);
    void save(CocktailEntity cocktail);
    List<CocktailEntity> findByNameContainingIgnoreCase(String search);
}
