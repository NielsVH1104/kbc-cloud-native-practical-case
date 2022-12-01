package com.ezgroceries.web;

import com.ezgroceries.meals.Meal;
import com.ezgroceries.meals.MealManager;
import com.ezgroceries.meals.MealController;
import com.ezgroceries.stub.StubMealManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class MealControllerTest {

    @InjectMocks
    MealController mealController;

    @Mock
    MealManager mealManager;

    @Test
    public void TestGetAllMeals(){
        given(mealManager.getAllMeals(any(String.class))).willReturn(new StubMealManager().getAllMeals("test"));

        List<Meal> meals = mealController.mealList("Spaghetti");
        assertThat(meals).isNotNull();
        assertThat(meals).isNotEmpty();
        assertThat(meals.size()).isEqualTo(2);

    }

}
