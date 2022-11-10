package com.ezgroceries.shoppinglist.list;

import com.ezgroceries.shoppinglist.cocktails.Cocktail;
import com.ezgroceries.shoppinglist.cocktails.CocktailManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class ShoppingListController {

    private static final Logger log = LoggerFactory.getLogger(ShoppingListController.class);

    private final ShoppingListManager shoppingListManager;

    private final CocktailManager cocktailManager;

    @Autowired
    public ShoppingListController(ShoppingListManager shoppingListManager, CocktailManager cocktailManager) {
        this.shoppingListManager=shoppingListManager;
        this.cocktailManager=cocktailManager;
    }

    @GetMapping(value = "/shopping-lists")
    public List<ShoppingList> getAllShoppingLists() {
        return shoppingListManager.getAllShoppingLists();
    }

    @PostMapping(value = "/shopping-lists", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addNewShoppingList(@RequestBody Map<String, String> body) {
        log.info("addNewShoppingListTriggered.");

        String name = body.get("name");

        log.info("Name: " + name);

        UUID newUuid = shoppingListManager.addNewList(name);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{resourceId}")
                .buildAndExpand(newUuid)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(value="/shopping-lists/{shoppingListId}")
    public ShoppingList getShoppingList(@PathVariable("shoppingListId") String shoppingListId){
        return shoppingListManager.getShoppingList(shoppingListId);
    }

    @PostMapping(value="/shopping-lists/{shoppingListId}/cocktails", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addCocktail(@PathVariable("shoppingListId") String shoppingListId,
                                      @RequestBody Map<String, String> body)
    {
        log.info("starts adding cocktail.");

        String cocktailId = body.get("cocktailId");
        log.info("cocktailId: " + cocktailId);
        log.info("shoppingListId: " + shoppingListId);

        ShoppingList shoppingList = shoppingListManager.getShoppingList(shoppingListId);
        Cocktail cocktail = cocktailManager.getCocktail(cocktailId);

        log.info("Cocktail: " + cocktail.getName());

        shoppingListManager.addCocktailToShoppingList(shoppingList, cocktail);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/shopping-lists/{resourceId}")
                .buildAndExpand(shoppingListId)
                .toUri();

        return ResponseEntity.created(location).build();


    }


}
