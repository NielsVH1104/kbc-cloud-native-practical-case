package com.ezgroceries.shoppinglist.list;

import com.ezgroceries.shoppinglist.cocktails.Cocktail;
import com.ezgroceries.shoppinglist.cocktails.CocktailEntity;
import com.ezgroceries.shoppinglist.cocktails.CocktailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ShoppingListService {

    private static final Logger log = LoggerFactory.getLogger(ShoppingListService.class);

    private final ShoppingListRepository shoppingListRepository;

    private final CocktailRepository cocktailRepository;


    public ShoppingListService(ShoppingListRepository shoppingListRepository, CocktailRepository cocktailRepository) {
        this.shoppingListRepository = shoppingListRepository;
        this.cocktailRepository = cocktailRepository;
    }

    public ShoppingList createNew(String name) {
        log.info("createNew triggered with name " + name);
        ShoppingList shoppingList = new ShoppingList(name);
        ShoppingListEntity entity = new ShoppingListEntity(shoppingList.getShoppingListId(), shoppingList.getName());

        shoppingListRepository.save(entity);

        return shoppingList;
    }

    public ShoppingList findExistingList(UUID uuid){
        ShoppingListEntity entity = shoppingListRepository.findByShoppingListId(uuid);
        return entityToShoppingList(entity);
    }

    public void addCocktailToList(ShoppingList list, Cocktail cocktail){
        log.info("addCocktailToList triggered. list: " + list.getName() + ", cocktail: " + cocktail.getName());
        ShoppingListEntity shoppingListEntity = shoppingListRepository.findByShoppingListId(list.getShoppingListId());
        CocktailEntity cocktailEntity = cocktailRepository.findByCocktailId(cocktail.getCocktailId());
        shoppingListEntity.addCocktail(cocktailEntity);
        cocktailEntity.addShoppingList(shoppingListEntity);

        shoppingListRepository.save(shoppingListEntity);
        cocktailRepository.save(cocktailEntity);
    }

    public List<ShoppingList> getAllLists(){
        List<ShoppingListEntity> entities =  shoppingListRepository.findAll();
        List<ShoppingList> shoppingListList = new ArrayList<>();
        for(ShoppingListEntity entity: entities){
            shoppingListList.add(entityToShoppingList(entity));
        }
        return shoppingListList;
    }

    private ShoppingList entityToShoppingList(ShoppingListEntity entity){
        ShoppingList list = new ShoppingList(entity.getName());
        list.setShoppingListId(entity.getShoppingListId());

        for(CocktailEntity cocktail: entity.getCocktails()){
            for(String ingredient: cocktail.getIngredients()){
                list.addIngredient(ingredient);
            }
        }

        return list;
    }
}
