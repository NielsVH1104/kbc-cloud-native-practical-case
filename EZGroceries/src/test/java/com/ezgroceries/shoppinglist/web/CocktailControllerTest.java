package com.ezgroceries.shoppinglist.web;

import com.ezgroceries.shoppinglist.cocktails.CocktailController;
import com.ezgroceries.shoppinglist.cocktails.CocktailManager;
import com.ezgroceries.shoppinglist.cocktails.StubCocktailManager;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CocktailController.class)
@ActiveProfiles("db")
@EnableFeignClients
public class CocktailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CocktailManager cocktailManager;

    @Test
    public void getAllCocktails() throws Exception{

        given(cocktailManager.getAllCocktails(any(String.class))).willReturn(new StubCocktailManager().getAllCocktails(""));

        mockMvc.perform(get("/cocktails?search='Russian'"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..cocktailId").isArray())
                .andExpect(jsonPath("$..cocktailId").isNotEmpty());
    }

    @Test
    public void getCoctailsWithoutSearch() throws Exception{
        mockMvc.perform(get("/cocktails"))
                .andExpect(status().is(400));
    }


}
