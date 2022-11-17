package com.ezgroceries.shoppinglist.Service;

import com.ezgroceries.shoppinglist.list.ShoppingList;
import com.ezgroceries.shoppinglist.list.ShoppingListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingListServiceTest {

    @MockBean
    private ShoppingListService shoppingListService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateNew(){
        String name = "Test1";
        ShoppingList shoppingList = shoppingListService.createNew(name);
        assertThat(shoppingList).isNotNull();
        assertThat(shoppingList.getName().equals(name));

    }
}
