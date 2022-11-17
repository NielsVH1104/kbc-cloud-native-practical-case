package com.ezgroceries.shoppinglist.Service;

import com.ezgroceries.shoppinglist.cocktails.CocktailRepository;
import com.ezgroceries.shoppinglist.list.ShoppingList;
import com.ezgroceries.shoppinglist.list.ShoppingListRepository;
import com.ezgroceries.shoppinglist.list.ShoppingListService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

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
}
