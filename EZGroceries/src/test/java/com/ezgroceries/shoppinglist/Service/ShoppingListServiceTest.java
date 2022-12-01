package com.ezgroceries.shoppinglist.Service;

import com.ezgroceries.shoppinglist.cocktails.Cocktail;
import com.ezgroceries.shoppinglist.cocktails.CocktailEntity;
import com.ezgroceries.shoppinglist.cocktails.CocktailRepository;
import com.ezgroceries.shoppinglist.list.ShoppingList;
import com.ezgroceries.shoppinglist.list.ShoppingListEntity;
import com.ezgroceries.shoppinglist.list.ShoppingListRepository;
import com.ezgroceries.shoppinglist.list.ShoppingListService;
import com.ezgroceries.shoppinglist.meals.Meal;
import com.ezgroceries.shoppinglist.meals.MealEntity;
import com.ezgroceries.shoppinglist.meals.MealRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ShoppingListServiceTest {

    @InjectMocks
    private ShoppingListService shoppingListService;

    @Mock
    private CocktailRepository cocktailRepository;

    @Mock
    private ShoppingListRepository shoppingListRepository;

    @Mock
    private MealRepository mealRepository;


    @Test
    public void testCreateNew(){
        String name = "Test1";
        ShoppingList shoppingList = shoppingListService.createNew(name);
        assertThat(shoppingList).isNotNull();
        assertThat(shoppingList.getName().equals(name)).isTrue();
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
        assertThat(result.getName().equals(testShoppingListEntity.getName())).isTrue();
        assertThat(result.getShoppingListId().equals(testShoppingListEntity.getShoppingListId())).isTrue();

        //test false
        result = shoppingListService.findExistingList(falseUuid);
        assertThat(result).isNull();

    }

    @Test
    public void testAddCocktailAndMealToList(){
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

        UUID mealUuid = UUID.randomUUID();
        Meal testMeal = new Meal("Cheese omelette");
        testMeal.addIngredient("cheese");
        testMeal.addIngredient("eggs");
        testMeal.setUuid(mealUuid);

        MealEntity testMealEntity = new MealEntity();
        testMealEntity.setMealId(mealUuid);
        testMealEntity.setIdMeal("1");
        testMealEntity.setIngredients(new HashSet<>(testMeal.getIngredients()));


        UUID shoppingUuid = UUID.randomUUID();
        ShoppingList testShoppingList = new ShoppingList("Test");
        testShoppingList.setShoppingListId(UUID.fromString(shoppingUuid.toString()));

        ShoppingListEntity testShoppingListEntity = new ShoppingListEntity();
        testShoppingListEntity.setName(testShoppingList.getName());
        testShoppingListEntity.setShoppingListId(shoppingUuid);

        given(cocktailRepository.findByCocktailId(cocktailUUid)).willReturn(testCocktailEntity);
        given(mealRepository.findByMealId(mealUuid)).willReturn(testMealEntity);
        given(shoppingListRepository.findByShoppingListId(shoppingUuid)).willReturn(testShoppingListEntity);

        shoppingListService.addCocktailToList(testShoppingList, testCocktail);
        shoppingListService.addMealToList(testShoppingList, testMeal);

        ShoppingList result = shoppingListService.findExistingList(shoppingUuid);

        assertThat(result).isNotNull();
        assertThat(result.getIngredients().containsAll(testCocktail.getIngredients())).isTrue();
        assertThat(result.getIngredients().containsAll(testMeal.getIngredients())).isTrue();


        ArrayList<ShoppingListEntity> testLists = new ArrayList<>();
        testLists.add(testShoppingListEntity);
        given(shoppingListRepository.findAll()).willReturn(testLists);

        List<ShoppingList> allLists = shoppingListService.getAllLists();
        assertThat(allLists).isNotNull();
        assertThat(allLists).isNotEmpty();
        assertThat(shoppingListsAreFunctionallyEqual(testShoppingList, allLists.get(0))).isTrue();
    }

    public static boolean shoppingListsAreFunctionallyEqual(ShoppingList list1, ShoppingList list2){
        boolean equal = true;

        if(!list1.getName().equals(list2.getName())) equal = false;
        else if(!list1.getShoppingListId().equals(list2.getShoppingListId())) equal = false;

        return equal;
    }
}
