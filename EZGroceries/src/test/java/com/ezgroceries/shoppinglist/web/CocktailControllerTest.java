package com.ezgroceries.shoppinglist.web;

import com.ezgroceries.shoppinglist.cocktails.CocktailController;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CocktailController.class)
public class CocktailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllCocktails() throws Exception{
        mockMvc.perform(get("/cocktails?search='Russian'"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..cocktailId").value(Lists.newArrayList("23b3d85a-3928-41c0-a533-6538a71e17c4","d615ec78-fe93-467b-8d26-5d26d8eab073")));
    }

    @Test
    public void getCoctailsWithoutSearch() throws Exception{
        mockMvc.perform(get("/cocktails"))
                .andExpect(status().is(400));
    }


}
