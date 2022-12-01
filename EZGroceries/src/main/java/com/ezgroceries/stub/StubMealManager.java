package com.ezgroceries.stub;

import com.ezgroceries.meals.Meal;
import com.ezgroceries.meals.MealManager;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Profile("stub")
public class StubMealManager implements MealManager {

    @Override
    public List<Meal> getAllMeals(String search) {
        ArrayList<Meal> meals = new ArrayList<>();

        Meal meal1 = new Meal("Spaghetti Bolognese");
        meal1.setUuid(UUID.fromString("173359d8-100b-48f9-9c14-331cb8e314cd"));
        meal1.setCategory("Beef");
        meal1.setInstructions("Put the onion and oil in a large pan and fry over a fairly high heat for 3-4 mins. " +
                "Add the garlic and mince and fry until they both brown. Add the mushrooms and herbs, and cook for " +
                "another couple of mins.\r\n\r\nStir in the tomatoes, beef stock, tomato ketchup or purée, " +
                "Worcestershire sauce and seasoning. Bring to the boil, then reduce the heat, cover and simmer, " +
                "stirring occasionally, for 30 mins.\r\n\r\nMeanwhile, cook the spaghetti in a large pan of boiling, " +
                "salted water, according to packet instructions. Drain well, run hot water through it, put it back in " +
                "the pan and add a dash of olive oil, if you like, then stir in the meat sauce. Serve in hot bowls " +
                "and hand round Parmesan cheese, for sprinkling on top.");
        meal1.setImage("https://www.themealdb.com/images/media/meals/sutysw1468247559.jpg");
        meal1.addIngredient("mushrooms");
        meal1.addIngredient("garlic");
        meal1.addIngredient("lean minced beef");
        meal1.addIngredient("onions");
        meal1.addIngredient("tomato puree");
        meal1.addIngredient("olive oil");
        meal1.addIngredient("hot beef stock");
        meal1.addIngredient("dried oregano");
        meal1.addIngredient("worcestershire sauce");
        meal1.addIngredient("parmesan");
        meals.add(meal1);

        Meal meal2 = new Meal("Spaghetti alla Carbonara");
        meal2.setUuid(UUID.fromString("3ddb2d79-69a2-4497-a97b-78c31fec72d4"));
        meal2.setCategory("Pasta");
        meal2.setImage("https://www.themealdb.com/images/media/meals/llcbn01574260722.jpg");
        meal2.setInstructions("STEP 1\r\nPut a large saucepan of water on to boil.\r\n\r\n" +
                        "STEP 2\r\nFinely chop the 100g pancetta, having first removed any rind. " +
                        "Finely grate 50g pecorino cheese and 50g parmesan and mix them together." +
                        "\r\n\r\nSTEP 3\r\nBeat the 3 large eggs in a medium bowl and season with a little freshly " +
                        "grated black pepper. Set everything aside.\r\n\r\nSTEP 4\r\nAdd 1 tsp salt to the boiling" +
                        " water, add 350g spaghetti and when the water comes back to the boil, cook at a constant " +
                        "simmer, covered, for 10 minutes or until al dente (just cooked).\r\n\r\nSTEP 5\r\nSquash 2 " +
                        "peeled plump garlic cloves with the blade of a knife, just to bruise it.\r\n\r\nSTEP 6\r\n" +
                        "While the spaghetti is cooking, fry the pancetta with the garlic. Drop 50g unsalted butter " +
                        "into a large frying pan or wok and, as soon as the butter has melted, tip in the pancetta and " +
                        "garlic.\r\n\r\nSTEP 7\r\nLeave to cook on a medium heat for about 5 minutes, stirring often, " +
                        "until the pancetta is golden and crisp. The garlic has now imparted its flavour, so take it out" +
                        " with a slotted spoon and discard.\r\n\r\nSTEP 8\r\nKeep the heat under the pancetta on low. " +
                        "When the pasta is ready, lift it from the water with a pasta fork or tongs and put it in the " +
                        "frying pan with the pancetta. Don’t worry if a little water drops in the pan as well " +
                        "(you want this to happen) and don’t throw the pasta water away yet.\r\n\r\nSTEP 9\r\nMix most" +
                        " of the cheese in with the eggs, keeping a small handful back for sprinkling over " +
                        "later.\r\n\r\nSTEP 10\r\nTake the pan of spaghetti and pancetta off the heat. Now quickly pour " +
                        "in the eggs and cheese. Using the tongs or a long fork, lift up the spaghetti so it mixes " +
                        "easily with the egg mixture, which thickens but doesn’t scramble, and everything is " +
                        "coated.\r\n\r\nSTEP 11\r\nAdd extra pasta cooking water to keep it saucy (several " +
                        "tablespoons should do it). You don’t want it wet, just moist. Season with a little salt, " +
                        "if needed.\r\n\r\nSTEP 12\r\nUse a long-pronged fork to twist the pasta on to the serving " +
                        "plate or bowl. Serve immediately with a little sprinkling of the remaining cheese and a " +
                        "grating of black pepper. If the dish does get a little dry before serving, splash in some " +
                        "more hot pasta water and the glossy sauciness will be revived.");
        meal2.addIngredient("Egg Yolks");
        meal2.addIngredient("Salt");
        meal2.addIngredient("Spaghetti");
        meal2.addIngredient("Bacon");
        meal2.addIngredient("Black Pepper");
        meal2.addIngredient("Pecorino");
        meals.add(meal2);
        return meals;
    }

    @Override
    public Meal getMeal(String uuid) {
        Meal meal1 = new Meal("Spaghetti Bolognese");
        meal1.setUuid(UUID.fromString("173359d8-100b-48f9-9c14-331cb8e314cd"));
        meal1.setCategory("Beef");
        meal1.setInstructions("Put the onion and oil in a large pan and fry over a fairly high heat for 3-4 mins. " +
                "Add the garlic and mince and fry until they both brown. Add the mushrooms and herbs, and cook for " +
                "another couple of mins.\r\n\r\nStir in the tomatoes, beef stock, tomato ketchup or purée, " +
                "Worcestershire sauce and seasoning. Bring to the boil, then reduce the heat, cover and simmer, " +
                "stirring occasionally, for 30 mins.\r\n\r\nMeanwhile, cook the spaghetti in a large pan of boiling, " +
                "salted water, according to packet instructions. Drain well, run hot water through it, put it back in " +
                "the pan and add a dash of olive oil, if you like, then stir in the meat sauce. Serve in hot bowls " +
                "and hand round Parmesan cheese, for sprinkling on top.");
        meal1.setImage("https://www.themealdb.com/images/media/meals/sutysw1468247559.jpg");
        meal1.addIngredient("mushrooms");
        meal1.addIngredient("garlic");
        meal1.addIngredient("lean minced beef");
        meal1.addIngredient("onions");
        meal1.addIngredient("tomato puree");
        meal1.addIngredient("olive oil");
        meal1.addIngredient("hot beef stock");
        meal1.addIngredient("dried oregano");
        meal1.addIngredient("worcestershire sauce");
        meal1.addIngredient("parmesan");
        return meal1;
    }
}
