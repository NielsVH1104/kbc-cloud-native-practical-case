package com.ezgroceries.cocktails;

import com.ezgroceries.converter.StringSetConverter;
import com.ezgroceries.shoppinglist.ShoppingListEntity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="cocktail")
public class CocktailEntity {

    @Id
    @Column(name = "ID")
    private UUID cocktailId;

    @Column(name="id_drink")
    private String idDrink;

    @Column(name="name")
    private String name;

    @Column(name="ingredients")
    @Convert(converter = StringSetConverter.class)
    private Set<String> ingredients;

    @ManyToMany
    @JoinTable(
            name = "cocktail_shopping_list",
            joinColumns = {@JoinColumn(name = "cocktail_id")},
            inverseJoinColumns = {@JoinColumn(name = "shopping_list_id")}
    )
    private Set<ShoppingListEntity> shoppingLists;

    @Column(name="glass")
    private String glass;

    @Column(name="image")
    private String image;

    @Column(name="instructions")
    private String instructions;

    public CocktailEntity(){
        ingredients = new HashSet<>();
        shoppingLists = new HashSet<>();
    }

    public void addIngredient(String ingredient){
        ingredients.add(ingredient);
    }

    public UUID getCocktailId() {
        return cocktailId;
    }

    public void setCocktailId(UUID cocktailId) {
        this.cocktailId = cocktailId;
    }

    public String getIdDrink() {
        return idDrink;
    }

    public void setIdDrink(String idDrink) {
        this.idDrink = idDrink;
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

    public void addShoppingList(ShoppingListEntity shoppingListEntity) {
        shoppingLists.add(shoppingListEntity);
    }

    public String getGlass() {
        return glass;
    }

    public void setGlass(String glass) {
        this.glass = glass;
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
}
