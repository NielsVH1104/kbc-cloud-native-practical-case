package com.ezgroceries.shoppinglist.web;

import com.ezgroceries.shoppinglist.cocktails.Cocktail;
import com.ezgroceries.shoppinglist.cocktails.CocktailController;
import com.ezgroceries.shoppinglist.cocktails.CocktailManager;
import com.ezgroceries.shoppinglist.stub.StubCocktailManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CocktailControllerTest {

    @InjectMocks
    private CocktailController cocktailController;

    @Mock
    CocktailManager cocktailManager;

    @Test
    public void testGetAllCocktails(){
        given(cocktailManager.getAllCocktails(any())).willReturn(new StubCocktailManager().getAllCocktails("test"));
        List<Cocktail> cocktails = cocktailController.cocktailList("Russian");
        assertThat(cocktails).isNotNull();
        assertThat(cocktails.size()).isEqualTo(2);
    }




}
