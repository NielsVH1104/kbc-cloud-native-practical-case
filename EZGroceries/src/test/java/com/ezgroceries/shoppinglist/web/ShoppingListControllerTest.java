package com.ezgroceries.shoppinglist.web;

import com.ezgroceries.shoppinglist.cocktails.Cocktail;
import com.ezgroceries.shoppinglist.cocktails.CocktailManager;
import com.ezgroceries.shoppinglist.list.ShoppingList;
import com.ezgroceries.shoppinglist.list.ShoppingListController;
import com.ezgroceries.shoppinglist.list.ShoppingListManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.URI;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShoppingListControllerTest {

    @InjectMocks
    private ShoppingListController shoppingListController;

    @Mock
    private ShoppingListManager shoppingListManager;

    @Mock
    private CocktailManager cocktailManager;

    @BeforeAll
    public static void setup(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }


    @Test
    public void CreateNewList() throws Exception {
        //Add a new Shopping List,
        given(shoppingListManager.addNewList(any())).willReturn(UUID.randomUUID());

        String name1 = "Shopping List 1";
        Map<String, String> shoppingListMap = new HashMap<>();
        shoppingListMap.put("name",name1);
        ResponseEntity response = shoppingListController.addNewShoppingList(shoppingListMap);
        assertThat(response.getStatusCode().equals(201));
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
        assertThat(result.getName().equals(correctList.getName()));
        assertThat(result.getIngredients()).isNotEmpty();
        assertThat(result.getIngredients().contains("Milk"));

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

        assertThat(response.getStatusCode().equals(200));

        ShoppingList result = shoppingListController.getShoppingList(shoppingUuid);

        assertThat(result.getIngredients()).isNotEmpty();
        assertThat(result.getIngredients()).size().isEqualTo(testCocktail.getIngredients().size());
    }
}
