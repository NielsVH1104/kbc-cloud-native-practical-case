package com.ezgroceries.shoppinglist.list;

import com.ezgroceries.shoppinglist.cocktails.CocktailEntity;
import com.ezgroceries.shoppinglist.meals.MealEntity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="shopping_list")
public class ShoppingListEntity {

    @Id
    @Column(name = "ID")
    private UUID shoppingListId;

    @Column(name="name")
    private String name;

    @ManyToMany(mappedBy = "shoppingLists")
    private Set<CocktailEntity> cocktails;

    @ManyToMany(mappedBy = "shoppingLists")
    private Set<MealEntity> meals;

    public ShoppingListEntity() {
        cocktails = new HashSet<>();
    }

    public ShoppingListEntity(UUID shoppingListId, String name) {
        this.shoppingListId = shoppingListId;
        this.name = name;
        cocktails = new HashSet<>();
        meals = new HashSet<>();
    }

    public UUID getShoppingListId() {
        return shoppingListId;
    }
    public void setShoppingListId(UUID shoppingListId) {
        this.shoppingListId = shoppingListId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Set<CocktailEntity> getCocktails() {
        return cocktails;
    }
    public void setCocktails(Set<CocktailEntity> cocktails) {
        this.cocktails = cocktails;
    }
    public void addCocktail(CocktailEntity cocktail){
        cocktails.add(cocktail);
    }
    public Set<MealEntity> getMeals() {return meals;}
    public void setMeals(Set<MealEntity> meals) {this.meals = meals;}
    public void addMeal(MealEntity meal){meals.add(meal);}
}
