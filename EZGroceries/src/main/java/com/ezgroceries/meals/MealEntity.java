package com.ezgroceries.meals;

import com.ezgroceries.converter.StringSetConverter;
import com.ezgroceries.shoppinglist.ShoppingListEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "meal")
public class MealEntity {

    @Id
    @Column(name = "id")
    private UUID mealId;

    @Column(name = "id_meal")
    private String idMeal;

    @Column(name="name")
    private String name;

    @Column(name="ingredients")
    @Convert(converter = StringSetConverter.class)
    private Set<String> ingredients;

    @ManyToMany
    @JoinTable(
            name = "meal_shopping_list",
            joinColumns = {@JoinColumn(name = "meal_id")},
            inverseJoinColumns = {@JoinColumn(name = "shopping_list_id")}
    )
    private Set<ShoppingListEntity> shoppingLists;

    @Column(name="image")
    private String image;

    @Column(name="instructions")
    private String instructions;

    @Column(name = "category")
    private String category;

    public MealEntity(){
        ingredients = new HashSet<>();
        shoppingLists = new HashSet<>();
    }

    public void addIngredient(String ingredient){
        ingredients.add(ingredient);
    }

    public void addShoppingList(ShoppingListEntity shoppingListEntity) {
        shoppingLists.add(shoppingListEntity);
    }

    public UUID getMealId() {
        return mealId;
    }

    public void setMealId(UUID mealId) {
        this.mealId = mealId;
    }

    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }

    public Set<ShoppingListEntity> getShoppingLists() {
        return shoppingLists;
    }

    public void setShoppingLists(Set<ShoppingListEntity> shoppingLists) {
        this.shoppingLists = shoppingLists;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
