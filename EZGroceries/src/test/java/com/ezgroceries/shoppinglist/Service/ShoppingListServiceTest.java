package com.ezgroceries.shoppinglist.Service;

import com.ezgroceries.shoppinglist.cocktails.Cocktail;
import com.ezgroceries.shoppinglist.cocktails.CocktailEntity;
import com.ezgroceries.shoppinglist.cocktails.CocktailRepository;
import com.ezgroceries.shoppinglist.list.ShoppingList;
import com.ezgroceries.shoppinglist.list.ShoppingListEntity;
import com.ezgroceries.shoppinglist.list.ShoppingListRepository;
import com.ezgroceries.shoppinglist.list.ShoppingListService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doAnswer;

@ExtendWith(MockitoExtension.class)
public class ShoppingListServiceTest {

    @InjectMocks
    private ShoppingListService shoppingListService;

    @Mock
    private CocktailRepository cocktailRepository;

    @Mock
    private ShoppingListRepository shoppingListRepository;


    @Test
    public void testCreateNew(){
        String name = "Test1";
        ShoppingList shoppingList = shoppingListService.createNew(name);
        assertThat(shoppingList).isNotNull();
        assertThat(shoppingList.getName().equals(name));
    }

    @Test
    public void testFindExistingList(){
        UUID correctUuid = UUID.randomUUID();
        ShoppingListEntity testShoppingListEntity = new ShoppingListEntity();
        testShoppingListEntity.setShoppingListId(correctUuid);
        testShoppingListEntity.setName("CocktailParty!!");
        UUID falseUuid = UUID.randomUUID();

        given(shoppingListRepository.findByShoppingListId(correctUuid)).willReturn(testShoppingListEntity);

        //test correct
        ShoppingList result = shoppingListService.findExistingList(correctUuid);
        assertThat(result).isNotNull();
        assertThat(result.getName().equals(testShoppingListEntity.getName()));
        assertThat(result.getShoppingListId().equals(testShoppingListEntity.getShoppingListId()));

        //test false
        result = shoppingListService.findExistingList(falseUuid);
        assertThat(result).isNull();

    }

    @Test
    public void testAddCocktailToList(){
        UUID cocktailUUid = UUID.randomUUID();
        Cocktail testCocktail = new Cocktail("Chocolate milk");
        testCocktail.setCocktailID(cocktailUUid.toString());
        testCocktail.addIngredient("Milk");
        testCocktail.addIngredient("Chocolate");

        CocktailEntity testCocktailEntity = new CocktailEntity();
        testCocktailEntity.setCocktailId(cocktailUUid);
        testCocktailEntity.setIdDrink("1");
        testCocktailEntity.setName(testCocktail.getName());
        testCocktailEntity.setIngredients(new HashSet<>(testCocktail.getIngredients()));


        UUID shoppingUuid = UUID.randomUUID();
        ShoppingList testShoppingList = new ShoppingList("Test");
        testShoppingList.setShoppingListId(UUID.fromString(shoppingUuid.toString()));

        ShoppingListEntity testShoppingListEnity = new ShoppingListEntity();
        testShoppingListEnity.setName(testShoppingList.getName());
        testShoppingListEnity.setShoppingListId(shoppingUuid);

        given(cocktailRepository.findByCocktailId(cocktailUUid)).willReturn(testCocktailEntity);
        given(shoppingListRepository.findByShoppingListId(shoppingUuid)).willReturn(testShoppingListEnity);

        Map<String, String> shoppingListMap = new HashMap<>();
        shoppingListMap.put("cocktailId",cocktailUUid.toString());
        shoppingListService.addCocktailToList(testShoppingList, testCocktail);

        ShoppingList result = shoppingListService.findExistingList(shoppingUuid);

        assertThat(result).isNotNull();
        assertThat(result.getIngredients().containsAll(testCocktail.getIngredients()));

    }
}
