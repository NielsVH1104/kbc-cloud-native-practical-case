package com.ezgroceries.web;

import com.ezgroceries.cocktails.CocktailManager;
import com.ezgroceries.shoppinglist.ShoppingListController;
import com.ezgroceries.shoppinglist.ShoppingListManager;
import com.ezgroceries.cocktails.Cocktail;
import com.ezgroceries.shoppinglist.ShoppingList;
import com.ezgroceries.meals.Meal;
import com.ezgroceries.meals.MealManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doAnswer;

@ExtendWith(MockitoExtension.class)
public class ShoppingListControllerTest {

    @InjectMocks
    private ShoppingListController shoppingListController;

    @Mock
    private ShoppingListManager shoppingListManager;

    @Mock
    private CocktailManager cocktailManager;

    @Mock
    private MealManager mealManager;

    @BeforeAll
    public static void setup(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }


    @Test
    public void CreateNewList() {
        //Add a new Shopping List,
        given(shoppingListManager.addNewList(any())).willReturn(UUID.randomUUID());

        String name1 = "Shopping List 1";
        Map<String, String> shoppingListMap = new HashMap<>();
        shoppingListMap.put("name",name1);
        ResponseEntity response = shoppingListController.addNewShoppingList(shoppingListMap);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getHeaders().getLocation()).isNotNull();
    }

    @Test
    public void testGetShoppingList(){

        String correctUuid = UUID.randomUUID().toString();
        ShoppingList correctList = new ShoppingList("Test1");
        correctList.setShoppingListId(UUID.fromString(correctUuid));
        correctList.addIngredient("Milk");
        String falseUuid = UUID.randomUUID().toString();

        given(shoppingListManager.getShoppingList(correctUuid)).willReturn(correctList);

        //test correct,
        ShoppingList result = shoppingListController.getShoppingList(correctUuid);
        assertThat(result).isNotNull();
        assertThat(result.getName().equals(correctList.getName())).isTrue();
        assertThat(result.getIngredients()).isNotEmpty();
        assertThat(result.getIngredients().contains("Milk")).isTrue();

        //test false
        result = shoppingListController.getShoppingList(falseUuid);
        assertThat(result).isNull();
    }

    @Test public void testAddCocktail(){
        String cocktailUUid = UUID.randomUUID().toString();
        Cocktail testCocktail = new Cocktail("Chocolate milk");
        testCocktail.setCocktailID(cocktailUUid);
        testCocktail.addIngredient("Milk");
        testCocktail.addIngredient("Chocolate");

        String shoppingUuid = UUID.randomUUID().toString();
        ShoppingList testShoppingList = new ShoppingList("Test");
        testShoppingList.setShoppingListId(UUID.fromString(shoppingUuid));

        given(cocktailManager.getCocktail(cocktailUUid)).willReturn(testCocktail);
        given(shoppingListManager.getShoppingList(shoppingUuid)).willReturn(testShoppingList);
        doAnswer((Answer<Void>) invocation -> {
            for(String ingredient: testCocktail.getIngredients()){
                testShoppingList.addIngredient(ingredient);
            }
            return null;
        }).when(shoppingListManager).addCocktailToShoppingList(isA(ShoppingList.class), isA(Cocktail.class));

        Map<String, String> shoppingListMap = new HashMap<>();
        shoppingListMap.put("cocktailId",cocktailUUid);
        ResponseEntity response = shoppingListController.addCocktail(shoppingUuid, shoppingListMap);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        ShoppingList result = shoppingListController.getShoppingList(shoppingUuid);

        assertThat(result.getIngredients()).isNotEmpty();
        assertThat(result.getIngredients()).size().isEqualTo(testCocktail.getIngredients().size());
    }
    @Test
    public void TestAddMeal(){
        String mealUuid = String.valueOf(UUID.randomUUID());
        Meal testMeal = new Meal("Cheese omelette");
        testMeal.addIngredient("cheese");
        testMeal.addIngredient("eggs");
        testMeal.setUuid(UUID.fromString(mealUuid));

        String shoppingUuid = UUID.randomUUID().toString();
        ShoppingList testShoppingList = new ShoppingList("Test");
        testShoppingList.setShoppingListId(UUID.fromString(shoppingUuid));

        given(mealManager.getMeal(mealUuid)).willReturn(testMeal);
        given(shoppingListManager.getShoppingList(shoppingUuid)).willReturn(testShoppingList);
        doAnswer((Answer<Void>) invocation -> {
            for(String ingredient: testMeal.getIngredients()){
                testShoppingList.addIngredient(ingredient);
            }
            return null;
        }).when(shoppingListManager).addMealToShoppingList(isA(ShoppingList.class), isA(Meal.class));

        Map<String, String> shoppingListMap = new HashMap<>();
        shoppingListMap.put("mealId",mealUuid);
        ResponseEntity response = shoppingListController.addMeal(shoppingUuid, shoppingListMap);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        ShoppingList result = shoppingListController.getShoppingList(shoppingUuid);
        assertThat(result.getIngredients()).isNotEmpty();
        assertThat(result.getIngredients()).size().isEqualTo(testMeal.getIngredients().size());

    }

    @Test
    public void TestGetShoppingLists(){
        String shoppingUuid = UUID.randomUUID().toString();
        ShoppingList testShoppingList = new ShoppingList("Test");
        testShoppingList.setShoppingListId(UUID.fromString(shoppingUuid));

        ArrayList<ShoppingList> testLists = new ArrayList<>();
        testLists.add(testShoppingList);

        given(shoppingListManager.getAllShoppingLists()).willReturn(testLists);

        List<ShoppingList> allLists = shoppingListController.getAllShoppingLists();

        assertThat(allLists).isNotNull();
        assertThat(allLists).isNotEmpty();
        assertThat(allLists.size()).isEqualTo(testLists.size());
        assertThat(allLists.get(0)).isEqualTo(testShoppingList);
    }

}
