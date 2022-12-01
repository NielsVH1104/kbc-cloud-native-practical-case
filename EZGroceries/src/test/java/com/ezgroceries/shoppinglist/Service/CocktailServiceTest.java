package com.ezgroceries.shoppinglist.Service;

import com.ezgroceries.shoppinglist.cocktails.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CocktailServiceTest {

    @InjectMocks
    private CocktailService cocktailService;

    @Mock
    private CocktailRepository cocktailRepository;

    private UUID cocktailUUid;
    private Cocktail testCocktail;
    private CocktailEntity testCocktailEntity;
    private UUID cocktailUuid2;
    private Cocktail testCocktail2;
    private CocktailEntity testCocktailEntity2;

    private CocktailDBResponse.DrinkResource resource1;
    private CocktailDBResponse.DrinkResource resource2;

    public void setup(){
        cocktailUUid = UUID.randomUUID();
        testCocktail = new Cocktail("Chocolate milk");
        testCocktail.setCocktailID(cocktailUUid.toString());
        testCocktail.addIngredient("Milk");
        testCocktail.addIngredient("Chocolate");

        testCocktailEntity = new CocktailEntity();
        testCocktailEntity.setCocktailId(cocktailUUid);
        testCocktailEntity.setIdDrink("1");
        testCocktailEntity.setName(testCocktail.getName());
        testCocktailEntity.setIngredients(new HashSet<>(testCocktail.getIngredients()));

        resource1 = new CocktailDBResponse.DrinkResource();
        resource1.setIdDrink("1");
        resource1.setStrIngredient1("Milk");
        resource1.setStrIngredient2("Chocolate");
        resource1.setStrDrinkThumb("");
        resource1.setStrDrink(testCocktail.getName());
        resource1.setStrInstructions("");
        resource1.setStrGlass("");

        cocktailUuid2 = UUID.randomUUID();
        testCocktail2 = new Cocktail("Tea with Lemon & sugar");
        testCocktail2.setCocktailID(cocktailUuid2.toString());
        testCocktail2.addIngredient("Tea");
        testCocktail2.addIngredient("Sugar");
        testCocktail2.addIngredient("Lemon juice");

        testCocktailEntity2 = new CocktailEntity();
        testCocktailEntity2.setCocktailId(cocktailUuid2);
        testCocktailEntity2.setIdDrink("2");
        testCocktailEntity2.setName(testCocktail2.getName());
        testCocktailEntity2.setIngredients(new HashSet<>(testCocktail2.getIngredients()));

        resource2 = new CocktailDBResponse.DrinkResource();
        resource2.setIdDrink("2");
        resource2.setStrIngredient1("Tea");
        resource2.setStrIngredient2("Sugar");
        resource2.setStrIngredient3("Lemon juice");
        resource2.setStrDrinkThumb("");
        resource2.setStrDrink(testCocktail2.getName());
        resource2.setStrInstructions("");
        resource2.setStrGlass("");

        given(cocktailRepository.findByIdDrink("1")).willReturn(testCocktailEntity);
        given(cocktailRepository.findByCocktailId(cocktailUUid)).willReturn(testCocktailEntity);
        given(cocktailRepository.findByIdDrink("2")).willReturn(testCocktailEntity2);
        given(cocktailRepository.findByCocktailId(cocktailUuid2)).willReturn(testCocktailEntity2);

    }

    @Test
    public void testCocktails(){
        setup();

        //test to see of correct cocktail is returned
        Cocktail foundCocktail = cocktailService.findCocktailByID(cocktailUUid.toString());

        assertThat(foundCocktail).isNotNull();
        assertThat(areCocktailsEqual(foundCocktail, testCocktail));

        //test when a different cocktail is returned
        Cocktail differentCocktail = cocktailService.findCocktailByID(cocktailUuid2.toString());

        assertThat(differentCocktail).isNotNull();
        assertThat(areCocktailsEqual(differentCocktail,testCocktail)).isFalse();

        //test Merge

        ArrayList<CocktailDBResponse.DrinkResource> resources = new ArrayList<>();
        resources.add(resource1);
        resources.add(resource2);
        CocktailDBResponse response = new CocktailDBResponse();
        response.setDrinks(resources);

        List<Cocktail> mergedCocktails = cocktailService.mergeCocktails(resources);

        assertThat(mergedCocktails).isNotNull();
        assertThat(mergedCocktails).isNotEmpty();
        assertThat(areCocktailsEqual(mergedCocktails.get(0),testCocktail));
        assertThat(areCocktailsEqual(mergedCocktails.get(1),testCocktail2));

        //test merge when one is not found
        given(cocktailRepository.findByIdDrink("2")).willReturn(null);

        mergedCocktails = cocktailService.mergeCocktails(resources);

        assertThat(mergedCocktails).isNotNull();
        assertThat(mergedCocktails).isNotEmpty();
        assertThat(areCocktailsEqual(mergedCocktails.get(0),testCocktail));
        assertThat(areCocktailsEqual(mergedCocktails.get(1),testCocktail2));

    }

    private boolean areCocktailsEqual(Cocktail cocktail1, Cocktail cocktail2){
        boolean equal = true;
        if(!cocktail1.getCocktailId().equals(cocktail2.getCocktailId())) equal =false;
        else if(!cocktail1.getName().equals(cocktail2.getName())) equal =false;
        return equal;
    }
}
