package com.ezgroceries.shoppinglist.web;

import com.ezgroceries.shoppinglist.list.ShoppingList;
import com.ezgroceries.shoppinglist.list.ShoppingListController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ShoppingListController.class)
public class ShoppingListControllerTest {

    @Autowired private MockMvc mockMvc;

    @Test
    public void CreateNewListAndAddCocktail() throws Exception {

        //first test that list is empty.
        mockMvc.perform(get("/shopping-lists"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));

        //then add a new value.

        ShoppingList newList = new ShoppingList("Jessica's Birthday");

        String json = "{\"name\":\"" + newList.getName() + "\"}";

        MvcResult result = mockMvc.perform( MockMvcRequestBuilders
                        .post("/shopping-lists")
                        .characterEncoding("utf-8")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        String uri = result.getResponse().getHeader("Location");
        assertThat(uri).isNotNull();
        assertThat(uri).isNotBlank();

        //check that the value exists

        mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(newList.getName()));

        //test adding a cocktail

        String cocktailId = "23b3d85a-3928-41c0-a533-6538a71e17c4";
        json="{\"cocktailId\":\"" + cocktailId + "\"}";

        String postUri = uri+"/cocktails";

        result = mockMvc.perform(MockMvcRequestBuilders
                    .post(postUri)
                    .characterEncoding("utf-8")
                    .content(json)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();


        String uri2 =  result.getResponse().getHeader("Location");
        assertThat(uri2).isNotNull();
        assertThat(uri2).isNotBlank();

        //check to see that the ingredients are added.

        result = mockMvc.perform(get(uri2))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(newList.getName()))
                .andExpect(jsonPath("$.ingredients").isArray())
                .andExpect(jsonPath("$.ingredients").isNotEmpty())
                .andReturn();

    }
}
